package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    public List<Emp> list();

    void deleteById(Integer id);

    void add(Emp emp);

    Emp selectById(Integer id);

    //不使用插件
    PageBean page1(Integer page, Integer pageSize);

    PageBean page(Integer page, Integer pageSize);

    PageBean pageByCondition(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    void deleteByIds(List<Integer> ids);

    void save(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
