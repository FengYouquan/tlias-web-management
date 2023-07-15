package com.youquan.controller;

import com.youquan.common.PageBean;
import com.youquan.common.Result;
import com.youquan.pojo.Emp;
import com.youquan.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;

@Slf4j
@RestController
public class EmpController {
    private final EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    /**
     * 条件查询员工数据
     *
     * @param name     姓名
     * @param gender   性别
     * @param begin    入职时间的开始时间
     * @param end      入职时间的结束时间
     * @param page     页码
     * @param pageSize 每页记录数
     * @return Result<PageBean < Emp>>
     */
    @GetMapping("/emps")
    public Result<PageBean<Emp>> list(String name,
                                      Short gender,
                                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                      @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                      @RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "5") Integer pageSize) {
        log.info("条件查询员工数据");
        PageBean<Emp> empPageBean = empService.list(name, gender, begin, end, page, pageSize);
        return Result.success(empPageBean);
    }

    /**
     * 批量删除员工数据
     *
     * @param ids 员工ID列表
     * @return Result<?>
     */
    @DeleteMapping("/emps/{ids}")
    public Result<?> delete(@PathVariable Integer[] ids) {
        log.info("批量删除员工数据，ids：{}", Arrays.toString(ids));
        empService.delete(ids);
        return Result.success();
    }
}
