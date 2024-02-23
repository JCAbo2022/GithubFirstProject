package com.example.mapper;

import com.example.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    @Select("select * from emp")
    public List<Emp> list();

    @Delete("delete from emp where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into emp (username, password, name, gender, image, job, dept_id, entrydate, create_time, update_time) values (#{username}, #{password}, #{name}, #{gender}, #{image}, #{job}, #{deptId}, #{entrydate}, #{createTime}, #{updateTime})")
    void add(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp selectById(Integer id);

    @Select("select count(*) from emp")
    Long count();

    @Select("select * from emp limit #{start}, #{pageSize}")
    List<Emp> pageList(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    @Select("select * from emp")
    List<Emp> pageByPlugins();

    List<Emp> listByCondition(@Param("name") String name, @Param("gender") Short gender, @Param("begin") LocalDate begin, @Param("end") LocalDate end);

    void deleteByIds(@Param("ids") List<Integer> ids);

    /*新增员工*/
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
    "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime});")
    void insert(Emp emp);

    /*根据id查询员工信息实现查询回显*/
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    /*修改员工信息*/
    void update(Emp emp);

    /*根据用户名和密码查询用户实现登录*/
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);

    /**
     * 根据部门id删除该部门下的员工数据
     * @param id
     */
    @Delete("delete from emp where dept_id = #{id}")
    void deletByDeptId(Integer id);
}
