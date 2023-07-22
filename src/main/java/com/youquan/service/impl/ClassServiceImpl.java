package com.youquan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youquan.anno.OperateLog;
import com.youquan.common.PageBean;
import com.youquan.exception.TliasException;
import com.youquan.mapper.ClassMapper;
import com.youquan.mapper.EmpMapper;
import com.youquan.mapper.StudentMapper;
import com.youquan.pojo.ClassEmp;
import com.youquan.pojo.Clazz;
import com.youquan.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Fengyouquan
 */
@RequiredArgsConstructor
@Service
public class ClassServiceImpl implements ClassService {
    private final ClassMapper classMapper;
    private final EmpMapper empMapper;
    private final StudentMapper studentMapper;


    /**
     * 保存班级对象到数据库中。
     *
     * @param clazz 要保存的班级对象
     * @throws TliasException 如果班级信息已经存在或者保存失败，抛出异常
     */
    @OperateLog
    @Override
    public void save(Clazz clazz) {
        // 检查开课时间和结课时间是否为空，如果为空则抛出异常
        if (clazz.getStartTime() == null || clazz.getFinishTime() == null) {
            throw new TliasException("200", "开课或结课时间不完整，请检查后重试");
        }

        // 判断课程的开始时间是否在结束时间之前
        if (!(clazz.getStartTime().isBefore(clazz.getFinishTime()))) {
            // 如果开始时间不在结束时间之前，抛出自定义异常
            throw new TliasException("200", "开课时间和结课时间不合理，请检查后重试");
        }

        // 获取班级对象的ID
        Integer id = clazz.getId();
        // 检查班级ID是否为空，并且数据库中是否已经存在该ID的记录
        if (id != null && classMapper.countById(id) > 0) {
            // 如果班级ID不为空且数据库中已存在该ID的记录，抛出异常
            throw new TliasException("200", "班级ID被占用，请检查后重试");
        }

        // 获取班级对象的名称
        String name = clazz.getName();
        // 检查班级名称是否为空，如果为空则抛出异常
        if (name == null) {
            throw new TliasException("200", "班级名称不完整，请检查后重试");
        }

        if (!(name.trim().matches("^[\\u4e00-\\u9fa5_a-zA-Z0-9]{4,30}$"))) {
            throw new TliasException("200", "班级名称非法，请检查后重试");
        }

        // 检查数据库中是否已经存在同名的班级信息，如果存在则抛出异常
        if (classMapper.countByName(name) > 0) {
            throw new TliasException("200", "班级信息已存在，无需重复添加");
        }

        Integer empId = clazz.getEmpId();
        if (empId != null && !(empMapper.countById(empId) > 0)) {
            throw new TliasException("200", "班主任信息不准确，请检查后重试");
        }

        String classesNumber = clazz.getClassesNumber();
        if (classesNumber != null) {
            if (!classesNumber.trim().matches("^[\\u4e00-\\u9fa5_a-zA-Z0-9]{1,20}$")) {
                throw new TliasException("200", "教室编号非法，请检查后重试");
            }

            List<Clazz> clazzes = classMapper.getByClassNumber(classesNumber);
            if (clazzes != null) {
                for (Clazz classDb : clazzes) {
                    if (classDb.getFinishTime().isAfter(clazz.getStartTime()) && classDb.getStartTime().isBefore(clazz.getFinishTime())) {
                        throw new TliasException("200", "当前教室在开课周期内，已被" + classDb.getName() + "占用，请检查后重试");
                    }
                }
            }
        }


        // 将当前时间设置为班级对象的创建时间和更新时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());

        // 将班级对象保存到数据库中，并返回保存结果，如果保存结果不等于1,说明保存失败，抛出异常
        if (!(classMapper.save(clazz) == 1)) {
            throw new TliasException("500", "班级信息添加失败，请稍后再试");
        }
    }

    /**
     * 根据ID删除班级信息
     *
     * @param id 班级ID
     */
    @OperateLog
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void remove(Integer id) {
        // 如果id为空或者在数据库中不存在对应的记录数大于0,抛出一个自定义的异常，提示班级ID不存在
        if (id == null || !(classMapper.countById(id) > 0)) {
            throw new TliasException("200", "班级ID不存在，请检查后重试");
        }

        if (classMapper.getById(id).getFinishTime().isAfter(LocalDate.now())) {
            throw new TliasException("200", "班级尚未结课，无法删除");
        }

        studentMapper.deleteByClassId(id);

        // 从数据库中删除对应id的记录，如果删除失败，抛出一个自定义的异常，提示班级信息删除失败
        if (!(classMapper.remove(id) == 1)) {
            throw new TliasException("500", "班级信息删除失败，请稍后再试");
        }
    }

    @OperateLog
    @Override
    public void update(Clazz clazz) {
        // 判断课程的开始时间是否在结束时间之前
        LocalDate startTime = clazz.getStartTime();
        LocalDate finishTime = clazz.getFinishTime();
        if (startTime != null && finishTime != null && !(startTime.isBefore(finishTime))) {
            // 如果开始时间不在结束时间之前，抛出自定义异常
            throw new TliasException("200", "开课时间和结课时间不合理，请检查后重试");
        }
        Integer id = clazz.getId();
        if (id == null) {
            throw new TliasException("200", "班级信息修改失败");
        }

        Integer empId = clazz.getEmpId();
        if (empId != null && !(empMapper.countById(empId) > 0)) {
            throw new TliasException("200", "班主任信息不准确，请检查后重试");
        }
        String name = clazz.getName();
        if (name != null) {
            if (!(name.trim().matches("^[\\u4e00-\\u9fa5_a-zA-Z0-9]{4,30}$"))) {
                throw new TliasException("200", "班级名称非法，请检查后重试");
            }

            if (classMapper.countByNameButId(id, name) > 0) {
                throw new TliasException("200", "班级名称冲突，请检查后重试");
            }
        }

        String classesNumber = clazz.getClassesNumber();
        if (classesNumber != null) {
            if (!classesNumber.trim().matches("^[\\u4e00-\\u9fa5_a-zA-Z0-9]{1,20}$")) {
                throw new TliasException("200", "教室编号非法，请检查后重试");
            }
            List<Clazz> clazzes = classMapper.getByClassNumberButId(id, classesNumber);
            if (clazzes != null) {
                for (Clazz classDb : clazzes) {
                    if (classDb.getFinishTime().isAfter(clazz.getStartTime()) && classDb.getStartTime().isBefore(clazz.getFinishTime())) {
                        throw new TliasException("200", "当前教室在开课周期内，已被" + classDb.getName() + "占用，请检查后重试");
                    }
                }
            }
        }


        clazz.setUpdateTime(LocalDateTime.now());

        if (!(classMapper.update(clazz) == 1)) {
            throw new TliasException("500", "班级信息更新失败，请稍后再试");
        }
    }

    @Override
    public PageBean<ClassEmp> list(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(page, pageSize);

        // 执行分页查询并获取分页结果
        Page<ClassEmp> clazzPage = classMapper.list(name, begin, end, page, pageSize);
        // 封装PageBean
        return new PageBean<>(clazzPage.getTotal(), clazzPage.getResult());
    }

    @Override
    public Clazz getById(Integer id) {
        if (id == null || !(classMapper.countById(id) > 0)) {
            throw new TliasException("200", "班级信息不存在");
        }
        Clazz clazz = classMapper.getById(id);
        if (clazz == null) {
            throw new TliasException("500", "班级信息查询失败，请稍后再试");
        }

        return clazz;
    }

}
