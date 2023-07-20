package com.youquan.controller;

import com.youquan.common.PageBean;
import com.youquan.common.Result;
import com.youquan.pojo.Clazz;
import com.youquan.service.ClassService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fengyouquan
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/classes")
public class ClassController {
    private final ClassService classService;

    /**
     * 添加班级信息的方法
     *
     * @param clazz 要保存的班级对象
     * @return 返回保存操作的结果
     */
    @PostMapping
    public Result<?> save(@RequestBody Clazz clazz) {
        // 打印日志，记录添加班级信息的操作
        log.info("添加班级信息：{}", clazz);

        // 调用classService的save方法，将班级信息保存到数据库或其他存储介质中
        classService.save(clazz);

        // 返回保存成功的操作结果
        return Result.success();
    }

    /**
     * 根据ID删除班级信息
     *
     * @param id 要删除的班级信息的ID
     * @return 返回操作成功的结果
     */
    @DeleteMapping("/{id}")
    public Result<?> remove(@PathVariable Integer id) {
        log.info("根据ID删除班级信息,ID:{}", id);

        // 调用classService的remove方法，执行删除操作
        classService.remove(id);
        return Result.success();
    }

    /**
     * 更新班级信息
     *
     * @param clazz 包含要更新的班级信息的Clazz对象
     * @return Result.success()表示更新成功
     */
    @PutMapping
    public Result<?> update(@RequestBody Clazz clazz) {
        // 记录日志，输出要修改的班级信息
        log.info("修改班级信息，{}", clazz);
        // 调用classService的update方法进行班级信息的更新操作
        classService.update(clazz);
        // 返回更新成功的Result对象
        return Result.success();
    }

    /**
     * 获取班级列表数据，支持分页和条件查询。
     *
     * @param name     班级名称
     * @param begin    开始日期(格式：yyyy-MM-dd)
     * @param end      结束日期(格式：yyyy-MM-dd)
     * @param page     当前页码，默认为1
     * @param pageSize 每页显示的记录数，默认为5
     * @return 返回包含班级列表数据的分页结果对象
     */
    @GetMapping
    public Result<PageBean<Clazz>> list(String name,
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "5") Integer pageSize) {
        log.info("分页和条件查询班级数据，{},{},{},{},{}", page, pageSize, name, begin, end);
        PageBean<Clazz> clazzPageBean = classService.list(name, begin, end, page, pageSize);
        return Result.success(clazzPageBean);
    }

}
