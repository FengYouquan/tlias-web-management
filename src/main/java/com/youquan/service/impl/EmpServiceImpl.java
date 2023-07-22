package com.youquan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youquan.anno.OperateLog;
import com.youquan.common.PageBean;
import com.youquan.exception.TliasException;
import com.youquan.mapper.EmpMapper;
import com.youquan.pojo.Emp;
import com.youquan.pojo.NameValue;
import com.youquan.service.EmpService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author Fengyouquan
 */
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

    @OperateLog
    @Override
    public void delete(Integer[] ids) {
        empMapper.delete(ids);
    }

    @OperateLog
    @Override
    public void save(Emp emp) {
        emp.setPassword("123456");
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.save(emp);
    }

    @Override
    public Emp getById(Integer id) {
        if (id == null) {
            return null;
        }
        return empMapper.getById(id);
    }

    @OperateLog
    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(String username, String password) {
        return empMapper.getByUsernameAndPassword(username, password);
    }

    @Override
    public List<NameValue> countByGender() {
        List<NameValue> countByGender = empMapper.countByGender();
        if (!(countByGender.size() == 2)) {
            throw new TliasException("500", "根据性别统计出错，请稍后再试");
        }
        for (NameValue nameValue : countByGender) {
            if (Objects.equals(nameValue.getName(), "1")) {
                nameValue.setName("男");
            } else {
                nameValue.setName("女");
            }
        }
        return countByGender;
    }
}
