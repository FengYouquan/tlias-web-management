package com.youquan.service;

import com.youquan.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门数据
     *
     * @return List<Dept> 部门数据列表
     */
    List<Dept> list();

    /**
     * 根据ID删除部门数据
     *
     * @param id 部门ID
     */
    void removeById(Integer id);

    /**
     * 添加部门数据
     *
     * @param dept 部门数据
     */
    void save(Dept dept);

    /**
     * 根据部门ID查询部门数据
     *
     * @param id 部门ID
     * @return Dept 部门数据
     */
    Dept getById(Integer id);

    /**
     * 修改部门数据
     *
     * @param dept 部门数据
     */
    void update(Dept dept);
}
