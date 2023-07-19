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
public class OperateLog {
    private Integer id; // 主键ID
    private Integer operateUser; // 操作人ID
    private LocalDateTime operateTime; // 操作时间
    private String className; // 操作类名
    private String methodName; // 操作方法名
    private String methodParams; // 操作方法参数
    private String returnValue; // 操作方法返回值
    private Long costTime; // 操作耗时
}