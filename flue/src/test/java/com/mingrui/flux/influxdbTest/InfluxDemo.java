//package com.mingrui.flux.influxdbTest;
//
//import org.influxdb.dto.QueryResult;
//
//import java.util.List;
//
///**
// * Created by yxzuo on 2022/6/30
// */
//
//public class InfluxDemo {
//    private static final InfluxDBUtil InfluxDBUtil = com.mingrui.flux.influxdbTest.InfluxDBUtil.setUp();
//    public static void main(String[] args) {
//        //查看数据库
//        testShowDB();
//        //创建数据库
//        String new_db = "new_db";
////        testCreateDB(new_db);
////        //查看数据库
//        testShowDB();
//    }
//
////    private static void testCreateDB(String new_db) {
////        InfluxDBUtil.createDB(new_db);
////    }
//
//    private static void testShowDB() {
//        QueryResult showDatabases = InfluxDBUtil.query("show databases");
//        System.out.println("数据库库名有：");
//        printResult(showDatabases);
//    }
//
//    /**
//     * 打印结果
//     * @param qr
//     */
//    public static void printResult(QueryResult qr){
//        for (QueryResult.Result rs : qr.getResults()){
//            for(QueryResult.Series sr:rs.getSeries()){
//                for(List values: sr.getValues()){
//                    for(Object value:values){
//                        System.out.print(value + " ");
//                    }
//                }
//                System.out.println();
//            }
//        }
//        System.out.println();
//    }
//}