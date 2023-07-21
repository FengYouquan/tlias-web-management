package com.youquan.mapper;

import com.github.pagehelper.Page;
import com.youquan.pojo.Student;
import com.youquan.pojo.StudentClass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Fengyouquan
 */
@Mapper
public interface StudentMapper {

    @Select("select count(*) from student where id = #{id}")
    int countById(Integer id);

    @Delete("delete from student where id = #{id}")
    int remove(Integer id);

    @Select("select count(*) from student where student_number = #{studentNumber}")
    int countByStudentNumber(String studentNumber);

    @Select("select count(*) from student where phone = #{phone}")
    int countByPhone(String phone);

    int save(Student student);

    int update(Student student);

    @Select("select id, name, student_number, gender, phone, highest_degree, classes_id,update_time from student where id = #{id}")
    Student getById(Integer id);

    Page<StudentClass> list(String name, Short highestDegree, Integer classesId, String studentNumber);
}