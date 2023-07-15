package com.youquan.mapper;

import com.github.pagehelper.Page;
import com.youquan.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

@Mapper
public interface EmpMapper {
    /**
     * 条件查询员工数据
     *
     * @param name   姓名
     * @param gender 性别
     * @param begin  入职时间的开始时间
     * @param end    入职时间的结束时间
     * @return Page<Emp>
     */
    Page<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除员工数据
     *
     * @param ids 员工ID列表
     */
    void delete(Integer[] ids);
}
