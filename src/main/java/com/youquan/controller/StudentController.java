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


    /**
     * 根据ID查询学员信息的方法。
     *
     * @param id 学员的ID
     * @return Result<Student> 包含学生信息的Result对象，如果成功则返回学生信息，否则返回错误信息
     */
    @GetMapping("/{id}")
    public Result<Student> getById(@PathVariable Integer id) {
        log.info("根据ID查询学员信息，id:{}", id);
        return Result.success(studentService.getById(id));
    }


    @PutMapping
    public Result<?> update(@RequestBody Student student) {
        // 记录一条日志，内容为"更新学员信息",后面跟着的是传入的学生对象的信息
        log.info("更新学员信息，{}", student);
        // 调用studentService的update方法，将传入的学生对象进行更新操作
        studentService.update(student);
        // 如果更新成功，则返回一个成功的Result对象
        return Result.success();
    }


    /**
     * 保存学生信息的方法。
     *
     * @param student 包含学生信息的请求体对象
     * @return Result.success() 表示操作成功并返回成功结果
     */
    @PostMapping
    public Result<?> save(@RequestBody Student student) {
        // 记录日志，输出新增的学生信息
        log.info("新增学员信息，{}", student);

        // 调用学生服务类的save方法保存学生信息
        studentService.save(student);

        // 返回成功的结果
        return Result.success();
    }


    /**
     * 查询学员列表数据的方法
     *
     * @param page          分页参数，表示当前页码，默认值为1。
     * @param pageSize      每页显示的记录数，默认值为10。
     * @param name          查询条件之一，表示学员姓名，用于模糊匹配。
     * @param highestDegree 查询条件之一，表示最高学历
     * @param classesId     查询条件之一，表示班级ID,用于筛选特定班级的学员。
     * @param studentNumber 查询条件之一，表示学员学号，用于精确匹配。
     * @return 返回一个包含分页结果的Result对象，其中封装了查询到的学生班级信息。
     */
    @GetMapping
    public Result<PageBean<StudentClass>> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, String name, Short highestDegree, Integer classesId, String studentNumber) {
        log.info("条件查询学员列表数据：{},{},{},{},{},{}", page, pageSize, name, highestDegree, classesId, studentNumber);
        // 调用studentService的list方法进行分页查询操作，并将结果赋值给studentPageBean变量。
        PageBean<StudentClass> studentPageBean = studentService.list(page, pageSize, name, highestDegree, classesId, studentNumber);

        // 将查询到的学生班级信息封装在Result对象中并返回。
        return Result.success(studentPageBean);
    }


    /**
     * 更新违纪扣分信息
     *
     * @param params 包含违纪扣分信息的请求体，包括id和disciplineScore
     * @return 返回操作结果
     */
    @PutMapping("/deductions")
    public Result<?> updateDeductionPoint(@RequestBody Map<String, Integer> params) {
        log.info("违纪扣分，params:{}", params);
        studentService.updateDeductionPoint(params.get("id"), params.get("disciplineScore"));
        return Result.success();
    }


    /**
     * 根据学历统计学员数量
     *
     * @return Result 统一响应结果
     */
    @PostMapping("/count")
    public Result<List<NameValue>> countByHighestDegree() {
        log.info("根据学历统计学员数量}");
        List<NameValue> countByHighestDegree = studentService.countByHighestDegree();
        return Result.success(countByHighestDegree);
    }
}
