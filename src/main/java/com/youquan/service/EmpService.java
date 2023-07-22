package com.youquan.service;

import com.youquan.common.PageBean;
import com.youquan.pojo.Emp;
import com.youquan.pojo.NameValue;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fengyouquan
 */
public interface EmpService {
    /**
     * 条件查询员工数据
     *
     * @param name     姓名
     * @param gender   性别
     * @param begin    入职时间的开始时间
     * @param end      入职时间的结束时间
     * @param page     页码
     * @param pageSize 每页记录数
     * @return PageBean<Emp>
     */
    PageBean<Emp> list(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

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
     * @return Emp 员工数据
     */
    Emp getById(Integer id);

    /**
     * 更新员工数据
     *
     * @param emp 员工数据
     */
    void update(Emp emp);

    /**
     * 登录校验
     *
     * @param username 用户名
     * @param password 密码
     * @return Emp 员工数据
     */
    Emp login(String username, String password);

    List<NameValue> countByGender();
}
