package com.example.controller;

import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Scope("prototype")
@RestController
@Slf4j
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result list(){
        log.info("查询所有部门信息");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("根据指定id= " + id + " 的部门信息");
        Dept dept = deptService.selectById(id);
        return Result.success(dept);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        log.info("根据指定id: " + id + " 删除指定部门");
        deptService.deleteById(id);

        empService.deleteByDeptId(id);

        return Result.success();
    }

    @PostMapping
    public Result insert(@RequestBody Dept dept){
        log.info("增加部门信息");
        deptService.add(dept);
        return Result.success();
    }

//    @PutMapping("/{id}")
//    public Result updateById(@PathVariable Integer id){
//        log.info("修改指定id= " + id + " 的部门的信息");
//        deptService.updateById(id);
//        return Result.success();
//    }

    @PutMapping("/{id}")
    public Result update(@PathVariable Integer id){
        log.info("修改指定id= " + id + " 的部门的信息");
        deptService.update(id);
        return Result.success();
    }
}
