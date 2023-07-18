package com.youquan.mapper;

import com.youquan.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门数据
     *
     * @return List<Dept>
     */
    @Select("select id, name, update_time from dept")
    List<Dept> list();

    /**
     * 根据ID删除部门数据
     *
     * @param id 部门ID
     */
    @Delete("delete from dept where id = #{id}")
    void removeById(Integer id);

    /**
     * 添加部门数据
     *
     * @param dept 部门数据
     */
    @Insert("insert into dept(name, create_time, update_time) VALUES (#{name},#{createTime},#{updateTime})")
    void save(Dept dept);

    @Select("select id, name from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     * 修改部门数据
     *
     * @param dept 部门数据
     */
    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);

    @Select("select count(*) from dept where name = #{name}")
    Integer countByName(String name);
}
