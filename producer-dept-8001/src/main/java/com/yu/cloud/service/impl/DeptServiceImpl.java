package com.yu.cloud.service.impl;

import com.yu.cloud.mapper.DeptMapper;
import com.yu.cloud.service.DeptService;
import com.yu.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;

    public boolean addDept(Dept dept) {
        return deptMapper.addDept(dept);
    }

    public Dept queryById(int id) {
        return deptMapper.queryById(id);
    }

    public List<Dept> queryAll() {
        return deptMapper.queryAll();
    }
}
