package com.example;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class FirstMybatisCrudApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testDelete(){
        empMapper.delete(6);
    }

    @Test
    public void testInsert(){
        Emp emp = new Emp();
        emp.setUsername("Tomss");
        emp.setName("酱菜阿阿波");
        emp.setImage("2.jpg");
        emp.setGender((short)1);
        emp.setEntrydate(LocalDate.of(2023, 12, 18));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(2);

        empMapper.insert(emp);
        System.out.println(emp.getId());
    }

    @Test
    public void testUpdate(){
        Emp emp = new Emp();
        emp.setId(28);
        emp.setUsername("Tomgg");
        emp.setName("汤姆1");
        emp.setImage("1.jpg");
        emp.setJob((short)1);
        emp.setGender((short)1);
        emp.setEntrydate(LocalDate.of(2023, 12, 20));
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);

        empMapper.update(emp);
    }

    @Test
    public void testGetById(){
        Emp emp = empMapper.getById(10);
        System.out.println(emp);
    }

    @Test
    public void testList(){
//        List<Emp> empList =  empMapper.list("周", (short)1, LocalDate.of(2023,1, 1),
//                LocalDate.of(2024,1,1));
//        List<Emp> empList =empMapper.list("周", null, null, null);
        List<Emp> empList =empMapper.list(null, null, null, null);
        System.out.println(empList);
    }

    @Test
    public void testUpdate1(){
        Emp emp = new Emp();
        emp.setId(28);
        emp.setUsername("Tomyy");
        emp.setName("汤姆ss");
        emp.setGender((short)2);
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update1(emp);
    }

    @Test
    public void testDeleteByIds(){
        List<Integer> integerList = Arrays.asList(27, 28);

        empMapper.deleteByIds(integerList);
    }

}
