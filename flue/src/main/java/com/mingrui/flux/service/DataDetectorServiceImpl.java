package com.mingrui.flux.service;

import com.github.pagehelper.PageHelper;
import com.mingrui.flux.dao.DataDetectorMapper;
import com.mingrui.flux.pojo.DataDetector;
import com.mingrui.flux.pojo.DataDetectorExample;
import com.mingrui.flux.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yxzuo on 2022/6/30
 */
@Service
public class DataDetectorServiceImpl implements DataDetectorService {

    @Autowired
    private DataDetectorMapper dataDetectorMapper;

    @Override
    public List<DataDetector> queryAll(Integer page, Integer limit) {
        DataDetectorExample example = new DataDetectorExample();
        PageHelper.startPage(page,limit);
        return dataDetectorMapper.selectByExample(example);
    }

    @Override
    public int update(DataDetector dataDetector) {
        return dataDetectorMapper.updateByPrimaryKey(dataDetector);
    }

    @Override
    public DataDetector queryById(String id) {
        return dataDetectorMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataDetector queryByStuId(long id) {
        return null;
    }
}
