package com.youquan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youquan.common.PageBean;
import com.youquan.exception.ClassException;
import com.youquan.mapper.ClassMapper;
import com.youquan.pojo.Clazz;
import com.youquan.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author Fengyouquan
 */
@RequiredArgsConstructor
@Service
public class ClassServiceImpl implements ClassService {
    private final ClassMapper classMapper;


    /**
     * 保存班级对象到数据库中。
     *
     * @param clazz 要保存的班级对象
     * @throws ClassException 如果班级信息已经存在或者保存失败，抛出异常
     */
    @Override
    public void save(Clazz clazz) {
        // 检查开课时间和结课时间是否为空，如果为空则抛出异常
        if (clazz.getStartTime() == null || clazz.getFinishTime() == null) {
            throw new ClassException("200", "开课或结课时间不完整，请检查后重试");
        }

        // 判断课程的开始时间是否在结束时间之前
        if (!(clazz.getStartTime().isBefore(clazz.getFinishTime()))) {
            // 如果开始时间不在结束时间之前，抛出自定义异常
            throw new ClassException("200", "开课时间和结课时间不合理，请检查后重试");
        }

        // 获取班级对象的ID
        Integer id = clazz.getId();
        // 检查班级ID是否为空，并且数据库中是否已经存在该ID的记录
        if (id != null && classMapper.countById(id) > 0) {
            // 如果班级ID不为空且数据库中已存在该ID的记录，抛出异常
            throw new ClassException("200", "班级ID被占用，请检查后重试");
        }

        // 获取班级对象的名称
        String name = clazz.getName();
        // 检查班级名称是否为空，如果为空则抛出异常
        if (name == null) {
            throw new ClassException("200", "班级名称不完整，请检查后重试");
        }
        // 检查数据库中是否已经存在同名的班级信息，如果存在则抛出异常
        if (classMapper.countByName(name) > 0) {
            throw new ClassException("200", "班级信息已存在，无需重复添加");
        }

        // 将当前时间设置为班级对象的创建时间和更新时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        // 将班级对象保存到数据库中，并返回保存结果，如果保存结果不等于1,说明保存失败，抛出异常
        if (!(classMapper.save(clazz) == 1)) {
            throw new ClassException("500", "班级信息添加失败，请稍后再试");
        }
    }

    /**
     * 根据ID删除班级信息
     *
     * @param id 班级ID
     */
    @Override
    public void remove(Integer id) {
        // 如果id为空或者在数据库中不存在对应的记录数大于0,抛出一个自定义的异常，提示班级ID不存在
        if (id == null || !(classMapper.countById(id) > 0)) {
            throw new ClassException("200", "班级ID不存在，请检查后重试");
        }
        // 从数据库中删除对应id的记录，如果删除失败，抛出一个自定义的异常，提示班级信息删除失败
        if (!(classMapper.remove(id) == 1)) {
            throw new ClassException("500", "班级信息删除失败，请稍后再试");
        }
    }

    @Override
    public void update(Clazz clazz) {
        // 检查开课时间和结课时间是否为空，如果为空则抛出异常
        if (clazz.getStartTime() == null || clazz.getFinishTime() == null) {
            throw new ClassException("200", "开课或结课时间不完整，请检查后重试");
        }

        // 判断课程的开始时间是否在结束时间之前
        if (!(clazz.getStartTime().isBefore(clazz.getFinishTime()))) {
            // 如果开始时间不在结束时间之前，抛出自定义异常
            throw new ClassException("200", "开课时间和结课时间不合理，请检查后重试");
        }

        // 检查数据库中是否已经存在同名的班级信息，如果存在则抛出异常
        if (classMapper.countByName(clazz.getName()) > 0) {
            throw new ClassException("200", "班级名称冲突，请检查后重试");
        }

        clazz.setUpdateTime(LocalDateTime.now());

        if (!(classMapper.update(clazz) == 1)) {
            throw new ClassException("500", "班级信息更新失败，请稍后再试");
        }
    }

    @Override
    public PageBean<Clazz> list(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);

        // 执行分页查询并获取分页结果
        Page<Clazz> clazzPage = classMapper.list(name, begin, end, page, pageSize);
        // 封装PageBean
        return new PageBean<>(clazzPage.getTotal(), clazzPage.getResult());
    }

}
