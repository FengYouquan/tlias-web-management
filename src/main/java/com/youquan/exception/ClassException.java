package com.youquan.exception;

import lombok.*;

/**
 * @author Fengyouquan
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ClassException extends RuntimeException {
    private String code;
    private String message;

    public ClassException(String message) {
        this.message = message;
    }
}
