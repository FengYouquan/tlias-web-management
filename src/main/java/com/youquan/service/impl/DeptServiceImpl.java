package com.youquan.service.impl;

import com.youquan.mapper.DeptMapper;
import com.youquan.mapper.EmpMapper;
import com.youquan.pojo.Dept;
import com.youquan.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Fengyouquan
 */
@RequiredArgsConstructor
@Service
public class DeptServiceImpl implements DeptService {
    private final DeptMapper deptMapper;
    private final EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeById(Integer id) {
        // 根据部门ID从deptMapper中删除部门数据
        deptMapper.removeById(id);

        // 调用empMapper的deleteByDeptId方法，根据部门ID删除关联的员工数据
        empMapper.deleteByDeptId(id);
    }

    @Override
    public void save(Dept dept) {
        // 查询数据库中是否已存在具有相同名称的部门
        Integer count = deptMapper.countByName(dept.getName());
        if (count > 0) {
            // 如果存在相同名称的部门，则抛出运行时异常
            throw new RuntimeException("部门已经存在");
        }

        // 查询数据库中是否已存在具有相同名称的部门
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 查询数据库中是否已存在具有相同名称的部门
        deptMapper.save(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
