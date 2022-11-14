package com.example.mx3.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author robot
 * @since 2020/4/2 8:30 下午
 */
@Component
public class ActiveComponent {

    @Value("${spring.profiles.active:prod}")
    private String active;

    public String getActive() {
        return active;
    }

    public boolean isProd() {
        return "prod".equals(active);
    }

}
