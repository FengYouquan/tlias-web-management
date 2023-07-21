package com.youquan.service;

import com.youquan.common.PageBean;
import com.youquan.pojo.ClassEmp;
import com.youquan.pojo.Clazz;

import java.time.LocalDate;

/**
 * @author Fengyouquan
 */
public interface ClassService {
    /**
     * 用于添加班级信息
     *
     * @param clazz 班级信息
     */
    void save(Clazz clazz);

    /**
     * 根据ID删除班级信息
     *
     * @param id 班级ID
     */
    void remove(Integer id);

    /**
     * 更新班级信息
     *
     * @param clazz 班级信息
     */
    void update(Clazz clazz);

    /**
     * 根据指定的名称、开始日期、结束日期、页码和每页大小，查询并返回一个PageBean对象。
     *
     * @param name     用于筛选的名称
     * @param begin    开始日期
     * @param end      结束日期
     * @param page     当前页码
     * @param pageSize 每页显示的数据条数
     * @return 包含查询结果的PageBean对象
     */
    PageBean<ClassEmp> list(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    /**
     * 根据给定的id获取对应的班级对象
     *
     * @param id 需要查询的班级对象的唯一标识符
     * @return 如果找到匹配的id, 则返回对应的班级对象；否则返回null。
     */
    Clazz getById(Integer id);
}