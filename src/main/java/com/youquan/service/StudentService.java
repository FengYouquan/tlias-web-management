package com.youquan.service;

import com.youquan.common.PageBean;
import com.youquan.mapper.StudentMapper;
import com.youquan.pojo.NameValue;
import com.youquan.pojo.Student;
import com.youquan.pojo.StudentClass;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Fengyouquan
 */
public interface StudentService {
    /**
     * 根据ID删除学员信息
     *
     * @param id 学员ID
     */
    void remove(Integer id);

    /**
     * 更新学生数据
     *
     * @param student 学生对象
     */
    void update(Student student);

    /**
     * 添加学生数据
     *
     * @param student 学生对象
     */
    void save(Student student);

    /**
     * 根据ID获取学生数据
     *
     * @param id 学生ID
     * @return Student 学生对象
     */
    Student getById(Integer id);

    /**
     * 分页查询学生数据
     *
     * @param page          页码
     * @param pageSize      每页显示的记录数
     * @param name          姓名
     * @param highestDegree 最高学历
     * @param classesId     班级ID
     * @param studentNumber 学号
     * @return PageBean 学生对象的分页数据集合
     */
    PageBean<StudentClass> list(Integer page, Integer pageSize, String name, Short highestDegree, Integer classesId, String studentNumber);

    /**
     * 根据学生ID进行违纪扣分
     *
     * @param id              学生ID
     * @param disciplineScore 扣除分数
     */
    void updateDeductionPoint(Integer id, Integer disciplineScore);

    /**
     * 根据学生的最高学历统计数量
     *
     * @return List 键值对集合
     */
    List<NameValue> countByHighestDegree();
}
