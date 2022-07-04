package com.mingrui.flux.controller;

import com.mingrui.flux.pojo.DataDetector;
import com.mingrui.flux.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by yxzuo on 2022/7/1
 */

@RestController
public class DataDetectorController {

    @Autowired
    private com.mingrui.flux.service.DataDetectorService dataDetectorService;

    @GetMapping("/list")
    public Object queryDataDetector(@RequestParam(name = "page",required = false,defaultValue = "1") Integer page,
                                    @RequestParam(name = "limit",required = false,defaultValue = "10") Integer limit) {
        List<DataDetector> dataDetector = dataDetectorService.queryAll(page,limit);
        return ResponseUtil.okList(dataDetector);
    }
}
