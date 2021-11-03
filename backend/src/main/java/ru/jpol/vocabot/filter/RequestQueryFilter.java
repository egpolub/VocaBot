package ru.jpol.vocabot.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ru.jpol.vocabot.security.jwt.JwtUserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Request query filter is used to change query parameters
 */
public class RequestQueryFilter extends GenericFilterBean {
    static final String USER_QUERY_PARAM = "userId";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            JwtUserDetails jwtUser = (JwtUserDetails) auth.getPrincipal();

            Long principalId;
            if ((principalId = jwtUser.getId()) != null) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

                String userId;
                // if the special request query parameter is not present,
                // then set it from the principal
                if ((userId = httpServletRequest.getParameter(USER_QUERY_PARAM)) == null ) {
                    CustomHttpRequestWrapper customHttpRequestWrapper = new CustomHttpRequestWrapper(httpServletRequest);
                    customHttpRequestWrapper.addParameter(USER_QUERY_PARAM, Long.toString(principalId));
                    filterChain.doFilter(customHttpRequestWrapper, httpServletResponse);
                }
                else {
                    try {
                        if (Long.parseLong(userId) != principalId) {
                            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                        }
                    } catch (NumberFormatException e) {
                        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    }
                }
            }
        }
    }
}
