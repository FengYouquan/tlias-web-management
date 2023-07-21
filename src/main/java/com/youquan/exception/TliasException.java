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
public class TliasException extends RuntimeException {
    private String code;
    private String message;

    public TliasException(String message) {
        this.message = message;
    }
}
