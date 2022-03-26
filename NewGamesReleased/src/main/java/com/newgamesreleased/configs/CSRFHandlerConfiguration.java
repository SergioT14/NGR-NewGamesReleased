package com.newgamesreleased.configs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CSRFHandlerConfiguration extends WebMvcConfigurerAdapter{
	
	@Override
	public void addInterceptors(InterceptorRegistry registro) {
		registro.addInterceptor(new CSRFHandlerInterceptor());
	}
}

class CSRFHandlerInterceptor implements HandlerInterceptor{
	
	@Override
	public void postHandle(final HttpServletRequest req, final HttpServletResponse respuesta
		, final Object handler, final ModelAndView modelAndView) throws Exception {

			if (modelAndView != null) {
				CsrfToken token = (CsrfToken) req.getAttribute("_csrf");
				if (token != null) {
					modelAndView.addObject("token", token.getToken());
				}
			}
	}
}