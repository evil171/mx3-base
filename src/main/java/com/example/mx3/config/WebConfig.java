package com.example.mx3.config;

import com.example.mx3.annotation.CurrentUser;
import com.example.mx3.component.ActiveComponent;
import com.example.mx3.constant.Constant;
import com.example.mx3.exception.NoLoginException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ActiveComponent activeComponent;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

        if (activeComponent.isProd()) {
            configurer.addPathPrefix("/_api_", config -> config.isAnnotationPresent(RestController.class));
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return parameter.hasParameterAnnotation(CurrentUser.class);
            }

            @Override
            public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                          NativeWebRequest request, WebDataBinderFactory webDataBinderFactory) {

                var currentUser = methodParameter.getParameterAnnotation(CurrentUser.class);

                return Optional
                        .ofNullable(request.getNativeRequest(HttpServletRequest.class))
                        .map(HttpServletRequest::getSession)
                        .map(session -> session.getAttribute(Constant.LOGIN_INFO))
                        .orElseGet(() -> {
                            if (currentUser != null && currentUser.required()) {
                                throw new NoLoginException();
                            } else {
                                return null;
                            }
                        });
            }
        });
    }

}