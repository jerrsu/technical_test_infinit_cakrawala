package com.technical.test.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jerry
 */

@Component
@Slf4j
public class JwtAuthenticationEntryPoint  implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        log.warn("Cek kembali credential anda.");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Maaf, Anda tidak berhak mengakses halaman ini.");
    }
}
