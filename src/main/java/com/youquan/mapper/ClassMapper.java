package com.youquan.mapper;

import com.github.pagehelper.Page;
import com.youquan.pojo.ClassEmp;
import com.youquan.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fengyouquan
 */
@Mapper
public interface ClassMapper {
    /**
     * 保存一个Clazz对象到数据库中。
     *
     * @param clazz 要保存的Clazz对象
     * @return 受影响的行数
     */
    int save(Clazz clazz);

    /**
     * 根据id查询数据库中是否存在对应的Clazz对象。
     *
     * @param id 要查询的Clazz对象的id
     * @return 如果存在则返回1, 否则返回0
     */
    @Select("select count(*) from classes where id = #{id}")
    int countById(Integer id);

    /**
     * 根据name查询数据库中是否存在对应的Clazz对象。
     *
     * @param name 要查询的Clazz对象的name
     * @return 如果存在则返回1, 否则返回0
     */
    @Select("select count(*) from classes where name = #{name}")
    int countByName(String name);

    /**
     * 从数据库中删除指定id的Clazz对象。
     *
     * @param id 要删除的Clazz对象的id
     */
    @Delete("delete from classes where id = #{id}")
    int remove(Integer id);

    /**
     * 根据指定的名称、开始日期、结束日期、页码和每页大小，查询并返回一个分页列表。
     *
     * @param name     用于筛选的名称
     * @param begin    开始日期，用于筛选在此日期之后的数据
     * @param end      结束日期，用于筛选在此日期之前的数据
     * @param page     当前页码，用于确定返回的数据范围
     * @param pageSize 每页显示的数据条数，用于确定返回的数据数量
     * @return 返回符合条件的分页列表
     */
    Page<ClassEmp> list(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    /**
     * 更新班级信息
     *
     * @param clazz 班级信息
     * @return 受影响的行数
     */
    int update(Clazz clazz);

    /**
     * 根据ID查询班级信息
     *
     * @param id 班级ID
     * @return Clazz 班级信息对象
     */
    @Select("select id, name, classes_number, start_time, finish_time, emp_id, update_time from classes where id = #{id}")
    Clazz getById(Integer id);

    /**
     * 根据教室编号获取所有的班级信息
     *
     * @param classesNumber 教室编号
     * @return List<Clazz> 班级集合
     */
    @Select("select  name, classes_number, start_time, finish_time from classes where classes_number = #{classesNumber}")
    List<Clazz> getByClassNumber(String classesNumber);

    /**
     * 根据班级名称和排除的ID统计班级数量
     *
     * @param id   班级ID
     * @param name 班级名称
     * @return int 影响的行数
     */
    @Select("select count(*) from classes where id <> #{id} and name = #{name}")
    int countByNameButId(Integer id, String name);

    /**
     * 根据教室编号和排除的ID获取班级列表
     *
     * @param id            班级ID
     * @param classesNumber 教室编号
     * @return List<Clazz> 班级集合
     */
    @Select("select  name, classes_number, start_time, finish_time from classes where id <> #{id} and classes_number = #{classesNumber} ")
    List<Clazz> getByClassNumberButId(Integer id, String classesNumber);
}
