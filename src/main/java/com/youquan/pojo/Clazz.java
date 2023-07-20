package com.youquan.pojo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Fengyouquan
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Clazz {
    private Integer id;
    private String name;
    private String classesNumber;
    private LocalDate startTime;
    private LocalDate finishTime;
    private Integer empId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Emp emp;
}
