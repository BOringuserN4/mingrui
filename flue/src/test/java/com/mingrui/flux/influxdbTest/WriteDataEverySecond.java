package com.mingrui.flux.influxdbTest;


import java.text.ParseException;
import java.time.Instant;
import java.util.Timer;
import java.util.TimerTask;

import com.influxdb.LogLevel;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.WriteOptions;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;

/**
 * Created by yxzuo on 2022/7/4
 */
public class WriteDataEverySecond {
    public static void main(final String[] args) throws ParseException {

        //
        // Parse configuration
        //
        CommandLine line = parseArgs(args);

        String url = line.getOptionValue("u", "http://localhost:9999");
        String token = line.getOptionValue("t", "my-token");
        String org = line.getOptionValue("o", "my-org");
        String bucket = line.getOptionValue("b", "my-bucket");

        //
        // Initialize client
        //
        InfluxDBClient client = InfluxDBClientFactory.create(url, token.toCharArray(), org, bucket)
                .setLogLevel(LogLevel.BASIC);

        //
        // Initialize write API with flush interval to 5sec
        //
        WriteApi writeApi = client.makeWriteApi(WriteOptions.builder().flushInterval(5_000).build());

        //
        // Produce DataPoint every seconds and pass it to WriteApi
        //
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Create DataPoint
                Point point = Point
                        .measurement("temperature")
                        .addField("value", Math.random() * 100)
                        .time(Instant.now(), WritePrecision.NS);

                System.out.println("Produced DataPoint: " + point.toLineProtocol());

                // Add Point to bath
                writeApi.writePoint(point);
            }
        }, 0, 1000);

        //
        // Close client and producer at exit
        //
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Close Client and Producer");
            timer.cancel();
            client.close();
        }));
    }

    /**
     * Parse command line arguments.
     *
     * @param args the list of atomic option and value tokens
     * @return parsed args
     * @throws ParseException if there are any problems while parsing args
     */
    private static CommandLine parseArgs(final String[] args) throws ParseException {

        Options options = new Options()
                .addOption("h", "help", false, "Print this help")
                .addOption("u", "url", true,
                        "The url to connect to the InfluxDB. Default 'http://localhost:9999'.")
                .addOption("t", "token", true,
                        "The token to use for the authorization. Default 'my-token'.")
                .addOption("o", "org", true,
                        "The name of an organization. Default 'my-org'.")
                .addOption("b", "bucket", true,
                        "The name of a bucket to write. Default 'my-bucket'.");

//        CommandLineParser parser = new DefaultParser();
//
//        CommandLine line = parser.parse(options, args);
//        if (line.hasOption("help")) {
//            HelpFormatter formatter = new HelpFormatter();
//            formatter.setWidth(2000);
//            formatter.printHelp("java WriteDataEverySecond", options, true);
//            System.exit(0);
//        }

        return null;
    }
}
