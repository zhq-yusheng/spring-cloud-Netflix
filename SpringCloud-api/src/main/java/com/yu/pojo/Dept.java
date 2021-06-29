package com.yu.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors // 支持链式编程
@NoArgsConstructor
public class Dept implements Serializable {
    private int dno;
    private String dname;
    private String dbname;
    public Dept(String dname){
        this.dname = dname;
    }
}
