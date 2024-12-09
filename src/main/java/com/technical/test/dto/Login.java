package com.technical.test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jerry
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login {
    private String username;
    private String password;
}
