package com.youquan.mapper;

import com.github.pagehelper.Page;
import com.youquan.pojo.Emp;
import com.youquan.pojo.NameValue;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

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

    /**
     * 根据ID统计员工数量
     *
     * @param id 员工ID
     * @return int 统计结果
     */
    @Select("select count(*) from emp where id = #{id}")
    int countById(Integer id);

    /**
     * 根据性别统计数量
     *
     * @return 返回键值对集合
     */
    List<NameValue> countByGender();

    /**
     * 根据ID获取部门名称
     *
     * @param id 部门ID
     * @return String 部门名称
     */
    String getDeptNameById(Integer id);

    /**
     * 根据ID修改员工密码
     *
     * @param id       员工ID
     * @param password 新密码
     * @return int 影响的行数
     */
    @Update("update emp set password = #{password} where id = #{id}")
    int password(Integer id, String password);

    /**
     * 根据员工ID获取登录密码
     *
     * @param id 员工ID
     * @return String 密码
     */
    @Select("select password from emp where id = #{id}")
    String getPasswordById(Integer id);
}
