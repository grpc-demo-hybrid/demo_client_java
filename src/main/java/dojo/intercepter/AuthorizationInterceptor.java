package dojo.intercepter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    
    @Value("${jwt.header}")
    private String tokenHeader;
    
    @Value("${server.servlet.context-path}")
    private String context;
    
    @Value("${jwt.validate.path}")
    private String validate;
    
	@Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        if (!StringUtils.equals(url, "hello")) {
            return true;
        }

        throw new RuntimeException("No power exception.");
    }
}