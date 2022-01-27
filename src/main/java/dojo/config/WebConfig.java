package dojo.config;

import dojo.filter.TokenFilter;
import dojo.intercepter.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	@Value("${jwt.validate.path}")
	private String validatePath;
	
	@Bean
	public FilterRegistrationBean tokenFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(tokenFilter());
		registrationBean.addUrlPatterns(validatePath + "/*");
		registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
		registrationBean.setOrder(3);
		return registrationBean;
	}

	@Autowired
	private DemoConfig demoConfig;
	
	@Bean
	public Filter tokenFilter() {
		TokenFilter tokenFilter = new TokenFilter();
		Set<String> cookieAuthPaths = new HashSet<>();
		tokenFilter.setCookieAuthPaths(cookieAuthPaths);
		return tokenFilter;
	}

	@Bean
	public AuthorizationInterceptor interceptor() {
		return new AuthorizationInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor()).addPathPatterns(validatePath + "/**");
	}
}
