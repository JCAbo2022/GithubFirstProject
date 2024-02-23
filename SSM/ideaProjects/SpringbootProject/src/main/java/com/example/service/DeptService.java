package com.example.service;

import com.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    /*
    * 查询所有部门信息
    * */
    public List<Dept> list();

    public void deleteById(Integer id) throws Exception;

    public void add(Dept dept);

    public void updateById(Integer id);

    public Dept selectById(Integer id);

    public void update(Integer id);
}
