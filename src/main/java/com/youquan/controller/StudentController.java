package com.youquan.controller;

import com.youquan.common.PageBean;
import com.youquan.common.Result;
import com.youquan.pojo.NameValue;
import com.youquan.pojo.Student;
import com.youquan.pojo.StudentClass;
import com.youquan.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Fengyouquan
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    /**
     * 根据ID删除学员信息
     *
     * @param id 要删除的学员的ID
     * @return 成功删除后的结果
     */
    @DeleteMapping("/{id}")
    public Result<?> remove(@PathVariable Integer id) {
        log.info("根据ID删除学员信息，ID：{}", id);
        studentService.remove(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<Student> getById(@PathVariable Integer id) {
        log.info("根据ID查询学员信息，id：{}", id);
        return Result.success(studentService.getById(id));
    }

    @PutMapping
    public Result<?> update(@RequestBody Student student) {
        log.info("更新学员信息，{}", student);
        studentService.update(student);
        return Result.success();
    }

    @PostMapping
    public Result<?> save(@RequestBody Student student) {
        log.info("新增学员信息，{}", student);
        studentService.save(student);
        return Result.success();
    }

    @GetMapping
    public Result<PageBean<StudentClass>> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, String name, Short highestDegree, Integer classesId, String studentNumber) {
        log.info("条件查询学员列表数据：{},{},{},{},{},{}", page, pageSize, name, highestDegree, classesId, studentNumber);
        PageBean<StudentClass> studentPageBean = studentService.list(page, pageSize, name, highestDegree, classesId, studentNumber);
        return Result.success(studentPageBean);
    }

    @PutMapping("/deductions")
    public Result<?> updateDeductionPoint(@RequestBody Map<String, Integer> params) {
        log.info("违纪扣分，params：{}", params);
        studentService.updateDeductionPoint(params.get("id"), params.get("disciplineScore"));
        return Result.success();
    }

    @PostMapping("/count")
    public Result<List<NameValue>> countByHighestDegree() {
        log.info("根据学历统计学员数量}");
        List<NameValue> countByHighestDegree = studentService.countByHighestDegree();
        return Result.success(countByHighestDegree);
    }
}
