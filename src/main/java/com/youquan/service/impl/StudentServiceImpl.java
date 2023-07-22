package com.youquan.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.youquan.anno.CheckPermission;
import com.youquan.anno.OperateLog;
import com.youquan.common.PageBean;
import com.youquan.exception.TliasException;
import com.youquan.mapper.ClassMapper;
import com.youquan.mapper.StudentMapper;
import com.youquan.pojo.NameValue;
import com.youquan.pojo.Student;
import com.youquan.pojo.StudentClass;
import com.youquan.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Fengyouquan
 */
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentMapper studentMapper;
    private final ClassMapper classMapper;

    @CheckPermission
    @OperateLog
    @Override
    public void remove(Integer id) {
        // 如果id为空或者在数据库中不存在对应的记录数大于0,抛出一个自定义的异常，提示学员ID不存在
        if (id == null || !(studentMapper.countById(id) > 0)) {
            throw new TliasException("200", "学员ID不存在，请检查后重试");
        }
        // 从数据库中删除对应id的记录，如果删除失败，抛出一个自定义的异常，提示班级信息删除失败
        if (!(studentMapper.remove(id) == 1)) {
            throw new TliasException("500", "学员信息删除失败，请稍后再试");
        }
    }

    @CheckPermission
    @OperateLog
    @Override
    public void update(Student student) {
        if (student.getId() == null) {
            throw new TliasException("200", "学员信息更新失败，请检查后重试");
        }
        String name = student.getName();
        if (name == null) {
            throw new TliasException("200", "学员姓名不能为空，请检查后重试");
        }
        Pattern namePattern = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9]{2,10}$");
        Matcher nameMatcher = namePattern.matcher(name.trim());
        if (!nameMatcher.matches()) {
            throw new TliasException("200", "学员姓名不准确，请检查后重试");
        }

        String studentNumber = student.getStudentNumber();
        if (studentNumber == null) {
            throw new TliasException("200", "学号不能为空，请检查后重试");
        }

        Pattern studentNumberPattern = Pattern.compile("^[a-zA-Z0-9]{10}$");
        Matcher studentNumberMatcher = studentNumberPattern.matcher(studentNumber.trim());
        if (!studentNumberMatcher.matches()) {
            throw new TliasException("200", "学号信息不准确，请检查后重试");
        }

        Short gender = student.getGender();
        if (gender == null || (gender != 1 && gender != 2)) {
            throw new TliasException("200", "学员性别信息不准确，请检查后重试");
        }

        String phone = student.getPhone();
        if (phone == null) {
            throw new TliasException("200", "学员手机号信息不能为空，请检查后重试");
        }
        Pattern phonePattern = Pattern.compile("^1[3-9]\\d{9}$");
        Matcher phoneMatcher = phonePattern.matcher(phone.trim());
        if (!phoneMatcher.matches()) {
            throw new TliasException("200", "学员手机号信息不准确，请检查后重试");
        }

        Short highestDegree = student.getHighestDegree();
        if (highestDegree != null && (highestDegree < 1 || highestDegree > 6)) {
            throw new TliasException("200", "学员最高学历信息不准确，请检查后重试");
        }


        student.setUpdateTime(LocalDateTime.now());

        if (!(studentMapper.update(student) == 1)) {
            throw new TliasException("500", "学员信息更新失败，请稍后再试");
        }

    }

    @CheckPermission
    @OperateLog
    @Override
    public void save(Student student) {
        String name = student.getName();
        if (name == null) {
            throw new TliasException("200", "学员姓名不能为空，请检查后重试");
        }
        Pattern namePattern = Pattern.compile("^[\\u4e00-\\u9fa5a-zA-Z0-9]{2,10}$");
        Matcher nameMatcher = namePattern.matcher(name.trim());
        if (!nameMatcher.matches()) {
            throw new TliasException("200", "学员姓名不准确，请检查后重试");
        }

        String studentNumber = student.getStudentNumber();
        if (studentNumber == null) {
            throw new TliasException("200", "学号不能为空，请检查后重试");
        }

        Pattern studentNumberPattern = Pattern.compile("^[a-zA-Z0-9]{10}$");
        Matcher studentNumberMatcher = studentNumberPattern.matcher(studentNumber.trim());
        if (!studentNumberMatcher.matches()) {
            throw new TliasException("200", "学号信息不准确，请检查后重试");
        }

        Short gender = student.getGender();
        if (gender == null || (gender != 1 && gender != 2)) {
            throw new TliasException("200", "学员性别信息不准确，请检查后重试");
        }

        String phone = student.getPhone();
        if (phone == null) {
            throw new TliasException("200", "学员手机号信息不能为空，请检查后重试");
        }
        Pattern phonePattern = Pattern.compile("^1[3-9]\\d{9}$");
        Matcher phoneMatcher = phonePattern.matcher(phone.trim());
        if (!phoneMatcher.matches()) {
            throw new TliasException("200", "学员手机号信息不准确，请检查后重试");
        }


        Short highestDegree = student.getHighestDegree();
        if (highestDegree != null && (highestDegree < 1 || highestDegree > 6)) {
            throw new TliasException("200", "学员最高学历信息不准确，请检查后重试");
        }

        if (studentMapper.countByStudentNumber(studentNumber) > 0) {
            throw new TliasException("200", "学员学号信息冲突，请检查后重试");
        }

        if (studentMapper.countByPhone(phone) > 0) {
            throw new TliasException("200", "学员手机号信息冲突，请检查后重试");
        }

        Integer classesId = student.getClassesId();
        if (classesId == null) {
            throw new TliasException("200", "所属班级信息不准确，请检查后重试");
        }
        if (!(classMapper.countById(classesId) > 0)) {
            throw new TliasException("200", "班级信息不存在，请检查后重试");
        }

        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        if (!(studentMapper.save(student) == 1)) {
            throw new TliasException("500", "学员信息添加失败，请稍后再试");
        }
    }

    @Override
    public Student getById(Integer id) {
        if (id == null) {
            throw new TliasException("200", "学员信息查询失败，请稍后再试");
        }
        return studentMapper.getById(id);
    }

    @Override
    public PageBean<StudentClass> list(Integer page, Integer pageSize, String name, Short highestDegree, Integer classesId, String studentNumber) {
        PageHelper.startPage(page, pageSize);

        Page<StudentClass> studentPage = studentMapper.list(name, highestDegree, classesId, studentNumber);

        return new PageBean<>(studentPage.getTotal(), studentPage.getResult());
    }

    @CheckPermission
    @OperateLog
    @Override
    public void updateDeductionPoint(Integer id, Integer disciplineScore) {
        if (id == null || !(studentMapper.countById(id) > 0)) {
            throw new TliasException("200", "违纪扣分失败，请稍后再试");
        }

        if (!(studentMapper.updateDeductionPoint(id, disciplineScore) == 1)) {
            throw new TliasException("500", "违纪扣分失败，请稍后再试");
        }
    }

    @Override
    public List<NameValue> countByHighestDegree() {
        List<NameValue> countByHighestDegree = studentMapper.countByHighestDegree();
        if (!(countByHighestDegree.size() <= 6)) {
            throw new TliasException("500", "根据最高学历统计出错，请稍后再试");
        }
        for (NameValue nameValue : countByHighestDegree) {
            switch (nameValue.getName()) {
                case "1" -> nameValue.setName("初中");
                case "2" -> nameValue.setName("高中");
                case "3" -> nameValue.setName("大专");
                case "4" -> nameValue.setName("本科");
                case "5" -> nameValue.setName("硕士");
                case "6" -> nameValue.setName("博士");
                default -> throw new TliasException("500", "根据最高学历统计出错，请稍后再试");
            }
        }
        return countByHighestDegree;
    }
}
