package com.youquan.controller;

import com.youquan.anno.OperateLog;
import com.youquan.common.PageBean;
import com.youquan.common.Result;
import com.youquan.pojo.Emp;
import com.youquan.pojo.NameValue;
import com.youquan.service.EmpService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * @author Fengyouquan
 */
@Slf4j
@RestController
@RequestMapping("/emps")
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
    @GetMapping
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
    @OperateLog
    @DeleteMapping("/{ids}")
    public Result<?> delete(@PathVariable Integer[] ids) {
        log.info("批量删除员工数据，ids：{}", Arrays.toString(ids));
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 新增员工数据
     *
     * @param emp 员工数据
     * @return Result
     */
    @OperateLog
    @PostMapping
    public Result<?> save(@RequestBody Emp emp) {
        log.info("新增员工数据，emp:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 根据ID查询员工数据
     *
     * @param id 员工ID
     * @return Emp 员工数据
     */
    @GetMapping("/{id}")
    public Result<Emp> getById(@PathVariable Integer id) {
        log.info("根据ID查询员工数据，id：{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * 更新员工数据
     *
     * @param emp 员工数据
     * @return Result
     */
    @OperateLog
    @PutMapping
    public Result<?> update(@RequestBody Emp emp) {
        log.info("更新员工数据，emp：{}", emp);
        empService.update(emp);
        return Result.success();
    }

    /**
     * 根据性别统计员工数量
     *
     * @return 返回一个包含员工性别统计结果的Result对象
     */
    @GetMapping("/count")
    public Result<List<NameValue>> countByGender() {
        // 记录日志信息
        log.info("根据性别统计员工数量");
        // 调用empService的countByGender方法获取员工性别统计结果
        List<NameValue> countByGender = empService.countByGender();
        // 将统计结果封装到Result对象中并返回
        return Result.success(countByGender);
    }

}
