package com.mingrui.flux.mysqlTest;

import com.mingrui.flux.pojo.DataDetector;
import org.junit.jupiter.api.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxzuo on 2022/6/30
 */
@Controller
public class DataDetectorService {

    @Autowired
    private com.mingrui.flux.service.DataDetectorService dataDetectorService;

    @Test
    public void testMbg() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("src/main/java/com/mingrui/flux/generator/MybaitsGenerater.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Test
    public Object queryDataDetector(){
        System.out.println("111111");
        DataDetector dataDetector = dataDetectorService.queryById("200");
        System.out.println("111111");
        return dataDetector;
    }

}
