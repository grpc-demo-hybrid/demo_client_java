package dojo.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class TokenFilter extends OncePerRequestFilter {
	private Set<String> cookieAuthPaths;
	
    public void setCookieAuthPaths(Set<String> cookieAuthPaths) {
		this.cookieAuthPaths = cookieAuthPaths;
	}

	@Value("${jwt.header}")
    private String tokenHeader;
    
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String url=request.getRequestURI();

		String token = getTokenFromRequest(request);
		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
    	String authToken="";
    	
    	authToken = request.getHeader(tokenHeader);
    	
    	if(authToken!=null&&!("".equals(authToken))){
    		return authToken;
    	}
    	
    	if (isCookieAuthApi(request)) {
    		Cookie[] cookies=request.getCookies();
    		if(cookies!=null){
    			for(int i=0;i<cookies.length;i++){
    				Cookie cookie=cookies[i];
    				if(tokenHeader.equals(cookie.getName())){
    					authToken=cookie.getValue();
    					break;
    				}
    			}
    		}
    	}
    	
		return authToken;
	}

	private boolean isCookieAuthApi(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		
		return cookieAuthPaths != null && cookieAuthPaths.contains(requestURI);
	}

}
