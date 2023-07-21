package com.youquan.pojo;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author Fengyouquan
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    private Integer id;
    private String name;
    private String studentNumber;
    private Short gender;
    private String phone;
    private Short highestDegree;
    private Integer classesId;
    private Integer disciplineTimes;
    private Integer disciplineScore;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
