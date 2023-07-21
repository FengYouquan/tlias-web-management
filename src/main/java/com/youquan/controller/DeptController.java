package com.youquan.controller;

import com.youquan.common.Result;
import com.youquan.pojo.Dept;
import com.youquan.service.DeptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Fengyouquan
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/depts")
public class DeptController {
    private final DeptService deptService;

    // @RequestMapping(value = "/depts", method = RequestMethod.GET)

    /**
     * 查询所有部门的数据
     *
     * @return Result<List < Dept>>
     */
    @GetMapping
    public Result<List<Dept>> list() {
        log.info("查询所有部门数据");

        List<Dept> depts = deptService.list();
        return Result.success(depts);
    }

    /**
     * 根据部门ID删除部门数据
     *
     * @param id 部门ID
     * @return 操作结果
     */

    @DeleteMapping("/{id}")
    public Result<?> removeById(@PathVariable Integer id) {
        // 记录日志，输出要删除的部门ID
        log.info("根据ID删除部门数据，id:{}", id);

        // 调用deptService的removeById方法，根据部门ID删除部门数据
        deptService.removeById(id);

        // 返回成功的结果
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