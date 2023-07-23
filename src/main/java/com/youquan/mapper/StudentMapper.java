package com.youquan.mapper;

import com.github.pagehelper.Page;
import com.youquan.pojo.NameValue;
import com.youquan.pojo.Student;
import com.youquan.pojo.StudentClass;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Fengyouquan
 */
@Mapper
public interface StudentMapper {
    /**
     * 根据ID统计学生数量
     *
     * @param id 学生ID
     * @return int 统计结果
     */
    @Select("select count(*) from student where id = #{id}")
    int countById(Integer id);

    /**
     * 根据ID删除员工数据
     *
     * @param id 员工ID
     * @return 影响的行数
     */
    @Delete("delete from student where id = #{id}")
    int remove(Integer id);

    /**
     * 根据学号统计学生数量
     *
     * @param studentNumber 学号
     * @return int 统计结果
     */
    @Select("select count(*) from student where student_number = #{studentNumber}")
    int countByStudentNumber(String studentNumber);

    /**
     * 根据手机号统计学生数量
     *
     * @param phone 手机号
     * @return int 统计结果
     */
    @Select("select count(*) from student where phone = #{phone}")
    int countByPhone(String phone);

    /**
     * 添加学生数据
     *
     * @param student 学生数据
     * @return int 影响的行数
     */
    int save(Student student);

    /**
     * 更新学生数据
     *
     * @param student 学生对象
     * @return int 影响的行数
     */
    int update(Student student);

    /**
     * 根据ID获取学生信息
     *
     * @param id 学生ID
     * @return Student 学生数据
     */
    @Select("select id, name, student_number, gender, phone, highest_degree, classes_id,update_time from student where id = #{id}")
    Student getById(Integer id);

    /**
     * 分页查询学生数据
     *
     * @param name          姓名
     * @param highestDegree 最高学历
     * @param classesId     班级ID
     * @param studentNumber 学号
     * @return Page 学生列表数据
     */
    Page<StudentClass> list(String name, Short highestDegree, Integer classesId, String studentNumber);


    /**
     * 根据ID对学生进行违纪扣分
     *
     * @param id              学生ID
     * @param disciplineScore 扣除的分数
     * @return int 影响的行数
     */
    @Update("update student set discipline_times = discipline_times+1, discipline_score = discipline_score+#{disciplineScore},update_time =now() where id = #{id}")
    int updateDeductionPoint(Integer id, Integer disciplineScore);

    /**
     * 根据学历统计学生数量
     *
     * @return List 键值对集合
     */
    List<NameValue> countByHighestDegree();

    /**
     * 根据班级ID删除学生数据
     *
     * @param classId 班级ID
     */
    @Delete("delete  from student where classes_id = #{classId}")
    void deleteByClassId(Integer classId);
}
