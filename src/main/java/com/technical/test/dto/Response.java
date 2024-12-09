package com.technical.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jerry
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response <T extends Object> {
    private String code;
    private String message;
    private T data;
}
