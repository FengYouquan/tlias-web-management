package com.youquan.mapper;

import com.github.pagehelper.Page;
import com.youquan.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

/**
 * @author Fengyouquan
 */
@Mapper
public interface EmpMapper {
    /**
     * 条件查询员工数据
     *
     * @param name   姓名
     * @param gender 性别
     * @param begin  入职时间的开始时间
     * @param end    入职时间的结束时间
     * @return Page<Emp>
     */
    Page<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除员工数据
     *
     * @param ids 员工ID列表
     */
    void delete(Integer[] ids);

    /**
     * 新增员工数据
     *
     * @param emp 员工数据
     */
    void save(Emp emp);

    /**
     * 根据ID查询员工数据
     *
     * @param id 员工ID
     * @return 员工数据
     */
    Emp getById(Integer id);

    /**
     * 更新员工数据
     *
     * @param emp 员工数据
     */
    void update(Emp emp);

    /**
     * 根据用户名或密码查询用户数据
     *
     * @param username 用户名
     * @param password 密码
     * @return Emp 用户数据
     */
    @Select("select id, username, name, gender from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(String username, String password);

    /**
     * 根据部门ID删除员工数据
     *
     * @param deptId 部门ID
     */
    @Delete("delete  from  emp where  dept_id = #{deptId}")
    void deleteByDeptId(Integer deptId);

    @Select("select count(*) from emp where id = #{id}")
    int countById(Integer id);
}
