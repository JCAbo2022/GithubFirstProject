package com.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import com.example.pojo.DeptLog;

@Mapper
public interface DeptLogMapper {

    @Insert("insert into dept_log (create_time, description) values (#{createTime}, #{description})")
    public void insert(DeptLog deptLog);

}
