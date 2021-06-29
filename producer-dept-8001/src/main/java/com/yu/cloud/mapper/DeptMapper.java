package com.yu.cloud.mapper;

import com.yu.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeptMapper {
    boolean addDept(Dept dept);
    Dept queryById(@Param("id") int id);
    List<Dept> queryAll();
}
