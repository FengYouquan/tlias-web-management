package com.youquan.pojo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 员工类
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Short gender;
    private String image;
    private Short job;
    private LocalDate entrydate;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
