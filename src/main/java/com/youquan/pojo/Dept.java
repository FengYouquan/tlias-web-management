package com.youquan.pojo;

import lombok.*;

import java.time.LocalDateTime;

/**
 * 部门类
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Dept {
    private Integer id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
