package com.eco.monitor.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 * Token Util.
 *
 * @author Maksym Shamanovskyi
 */
public final class TokenUtil {

    private static final Logger LOG = LoggerFactory.getLogger(TokenUtil.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_SCHEMA = "bearer";

    private TokenUtil() {
    }

    public static Optional<String> resolveToken(HttpServletRequest request) {
        var bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && (bearerToken.toLowerCase().startsWith(AUTHORIZATION_SCHEMA))) {
            /* Get token */
            var parts = bearerToken.split(" ");
            return parts.length == 2 ? of(parts[1]) : empty();
        }
        return empty();
    }

}