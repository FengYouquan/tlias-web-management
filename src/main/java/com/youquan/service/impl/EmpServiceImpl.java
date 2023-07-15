package com.youquan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youquan.common.PageBean;
import com.youquan.mapper.EmpMapper;
import com.youquan.pojo.Emp;
import com.youquan.service.EmpService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmpServiceImpl implements EmpService {
    private final EmpMapper empMapper;

    public EmpServiceImpl(EmpMapper empMapper) {
        this.empMapper = empMapper;
    }

    @Override
    public PageBean<Emp> list(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        // 设置分页参数,页码和每页显示的记录数
        PageHelper.startPage(page, pageSize);

        // 执行分页查询并获取分页结果
        Page<Emp> empPage = empMapper.list(name, gender, begin, end);

        // 封装PageBean
        return new PageBean<>(empPage.getTotal(), empPage.getResult());
    }

    @Override
    public void delete(Integer[] ids) {
        if (ids.length <= 0) {
            return;
        }
        empMapper.delete(ids);
    }
}
