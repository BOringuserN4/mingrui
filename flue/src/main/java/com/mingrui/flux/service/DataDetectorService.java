package com.mingrui.flux.service;

import com.mingrui.flux.pojo.DataDetector;

import java.util.List;

/**
 * Created by yxzuo on 2022/6/30
 */
public interface DataDetectorService {

    List<DataDetector> queryAll(Integer page, Integer limit);

    int update(DataDetector dataDetector);

    DataDetector queryById(String id);

    DataDetector queryByStuId(long id);

}
