package com.example.service.impl;

import com.example.mapper.DeptLogMapper;
import com.example.mapper.DeptMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.Dept;
import com.example.pojo.DeptLog;
import com.example.service.DeptLogService;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list(){

        return deptMapper.list();
    }

//    @Transactional注解用在业务层的方法上，类上和接口上，将当前方法（类，接口）交给spring进行事务管理，成功执行则提交事务，反之回滚事务
    @Transactional(rollbackFor = Exception.class /*不添加则默认只有抛出运行时异常才执行回滚操作，添加则抛出任何异常都会执行回滚操作*/)
    @Override
    public void deleteById(Integer id) throws Exception {
        try{
            deptMapper.deleteById(id);

            if(true){
                throw new Exception("抛错啦啦啦啦！！！");
            }

            empMapper.deletByDeptId(id);
        }
        finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行部门删除操作，删除id为" + id + "的部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.inser(dept);
    }

    @Override
    public void updateById(Integer id) {
        Dept dept = deptMapper.selectById(id);
        dept.setName("软件部");
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateById(dept);
    }

    @Override
    public Dept selectById(Integer id) {

        return deptMapper.selectById(id);
    }

    @Override
    public void update(Integer id) {
        Dept dept = deptMapper.selectById(id);
        dept.setName("项目部");
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateById(dept);
    }

}
