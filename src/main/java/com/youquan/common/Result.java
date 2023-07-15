package com.youquan.common;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Result<T> {
    private Integer code;// 1 成功 , 0 失败
    private String msg; // 提示信息
    private T data; // 数据 data

    /**
     * 增删改 成功响应
     *
     * @return Result<?>
     */
    public static Result<?> success() {
        return new Result<>(1, "success", null);
    }

    /**
     * 查询 成功响应
     *
     * @param data 数据
     * @param <T>  类型
     * @return <T> Result<T>
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(1, "success", data);
    }

    /**
     * 失败响应
     *
     * @param msg 信息
     * @return Result<?>
     */
    public static Result<?> error(String msg) {
        return new Result<>(0, msg, null);
    }
}