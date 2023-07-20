package com.youquan.service;

import com.youquan.common.PageBean;
import com.youquan.pojo.Clazz;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

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
    PageBean<Clazz> list(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);
}