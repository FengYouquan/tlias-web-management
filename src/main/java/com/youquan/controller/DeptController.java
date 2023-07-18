package com.youquan.controller;

import com.youquan.common.Result;
import com.youquan.pojo.Dept;
import com.youquan.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    private final DeptService deptService;

    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }

    // @RequestMapping(value = "/depts", method = RequestMethod.GET)

    /**
     * 查询所有部门的数据
     *
     * @return Result<List < Dept>>
     */
    @GetMapping
    public Result<List<Dept>> list() {
        log.info("查询所有部门数据");

        // 校验令牌的合法性


        List<Dept> depts = deptService.list();
        return Result.success(depts);
    }

    /**
     * 根据ID删除部门数据
     *
     * @param id 部门ID
     * @return Result<?>
     */
    @DeleteMapping("/{id}")
    public Result<?> removeById(@PathVariable Integer id) {
        log.info("根据ID删除部门数据,id:{}", id);
        deptService.removeById(id);
        return Result.success();
    }

    /**
     * 添加部门数据
     *
     * @param dept 部门数据
     * @return Result<?>
     */
    @PostMapping
    public Result<?> save(@RequestBody Dept dept) {
        log.info("添加部门数据，dept:{}", dept);
        deptService.save(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门数据
     *
     * @param id 部门ID
     * @return Result<Dept>
     */
    @GetMapping("/{id}")
    public Result<Dept> getById(@PathVariable Integer id) {
        log.info("根据ID查询部门数据,id:{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门数据
     *
     * @param dept 部门数据
     * @return Result
     */
    @PutMapping
    public Result<?> update(@RequestBody Dept dept) {
        log.info("修改部门数据，dept:{}", dept);
        deptService.update(dept);
        return Result.success();
    }
}