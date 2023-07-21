package com.youquan.service;

import com.youquan.common.PageBean;
import com.youquan.mapper.StudentMapper;
import com.youquan.pojo.Student;
import com.youquan.pojo.StudentClass;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    void update(Student student);

    void save(Student student);

    Student getById(Integer id);

    PageBean<StudentClass> list(Integer page, Integer pageSize, String name, Short highestDegree, Integer classesId, String studentNumber);
}
