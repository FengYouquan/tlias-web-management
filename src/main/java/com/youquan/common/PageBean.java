package com.youquan.common;

import lombok.*;

import java.util.List;

/**
 * 分页查询结果封装类
 * @author Fengyouquan
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PageBean<T> {

    private Long total;
    private List<T> rows;
}