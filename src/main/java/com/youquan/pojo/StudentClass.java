package com.youquan.pojo;


import lombok.*;

/**
 * @author Fengyouquan
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentClass extends Student {
    private Clazz clazz;
}
