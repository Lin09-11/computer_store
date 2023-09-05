package com.example.mycomputerstore.config;

import com.example.mycomputerstore.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理器拦截器的注册
 * 黑名单(在用户登录的状态下才可以访问的页面资源)和
 * 白名单(哪些资源可以在不登录的情况下访问:①register.html②login.html③index.html④/users/reg⑤/users/login⑥静态资源):
 */
@Configuration//加载当前的拦截器并进行注册
public class LoginInterceptorConfigurer implements WebMvcConfigurer {


    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义的拦截器对象
        HandlerInterceptor interceptor=new LoginInterceptor();
        // 白名单
        List<String> patterns = new ArrayList<String>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");
        patterns.add("/districts/**");
        //通过注册工具，完成注册拦截器
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")//表示要拦截的url
                .excludePathPatterns(patterns)//除了什么路径之外
        ;
    }
}
