package com.example.controller;

import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

//    @GetMapping
//    public Result list(){
//        log.info("查询所有员工信息");
//        List<Emp> empList = empService.list();
//        return Result.success(empList);
//    }
//
//    @GetMapping("/{id}")
//    public Result selectById(@PathVariable Integer id){
//        log.info("查询指定id= " + id + " 的员工的信息");
//        Emp emp = empService.selectById(id);
//        return Result.success(emp);
//    }

//    @DeleteMapping("/{id}")
//    public Result deleteById(@PathVariable Integer id){
//        log.info("根据id " + id + " 删除员工信息");
//        empService.deleteById(id);
//        return Result.success();
//    }

    /*新增员工*/
    @PostMapping
    public Result save(@RequestBody Emp emp){
        //记录日志
        log.info("新增员工，emp:{}", emp);
        //调用业务层新增功能
        empService.save(emp);
        //响应
        return Result.success();
    }


    /*分页查询*/
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
//        log.info("分页查询，参数:{}， {}", page, pageSize);
//        PageBean pageBean = empService.page(page, pageSize);
//        return Result.success(pageBean);
//    }

    /*条件分页查询*/
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate begin, @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("分页查询，参数:{}，{}，{}，{}，{}，{}", page, pageSize, name, gender, begin, end);
        PageBean pageBean = empService.pageByCondition(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    /*批量删除*/
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        log.info("批量删除ids:{}", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据ID查询员工信息id:{}",id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息为{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
