package com.yu.cloud.service;

import com.yu.pojo.Dept;

import java.util.List;

public interface DeptService {
    boolean addDept(Dept dept);
    Dept queryById(int id);
    List<Dept> queryAll();
}
