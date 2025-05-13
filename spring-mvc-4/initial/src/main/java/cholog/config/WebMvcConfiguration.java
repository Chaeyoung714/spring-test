package cholog.config;

import cholog.ui.AuthenticationPrincipalArgumentResolver;
import cholog.ui.CheckLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    // TODO: "/" 요청 시 hello.html 페이지 응답하기
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("hello");
    }

    // TODO: "/admin/**" 요청 시 LoginInterceptor 동작하게 하기
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckLoginInterceptor()).addPathPatterns("/admin/**");
    }

    // TODO: AuthenticationPrincipalArgumentResolver 등록하기
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthenticationPrincipalArgumentResolver());
    }
}
