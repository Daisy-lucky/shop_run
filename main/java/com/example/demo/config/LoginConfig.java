package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Handler;

@Configuration
public class LoginConfig  extends WebMvcConfigurerAdapter {

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return new SecurityInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/err");
        addInterceptor.excludePathPatterns("bootstrap/css/bootstrap.min.css");
        addInterceptor.excludePathPatterns("bootstrap/css/bootstrap-responsive.min.css");
        addInterceptor.excludePathPatterns("css/theme.css");
        addInterceptor.excludePathPatterns("images/icons/css/font-awesome.css");
        addInterceptor.excludePathPatterns("/help");
        addInterceptor.excludePathPatterns("/login");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            HttpSession session = request.getSession();
            if (session.getAttribute("login") != null)
                return true;

            // 跳转登录
            String url = "/help";
            response.sendRedirect(url);
            return false;
        }
    }
}
