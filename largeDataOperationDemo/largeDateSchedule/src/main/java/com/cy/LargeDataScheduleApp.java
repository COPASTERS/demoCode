package com.cy;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class LargeDataScheduleApp {
    /**
     * 默认启动定时器设置
     * 测试时可以按需设置 @TestPropertySource(properties = "app.scheduling.enable=false") 不启动定时器
     */
    @ConditionalOnProperty(
            value = "app.scheduling.enable", havingValue = "true", matchIfMissing = true
    )
    @Configuration
    @EnableScheduling
    public static class SchedulingConfiguration {
    }

    /**
     * Common
     */
    private static SpringApplicationBuilder configureSpringBuilder(SpringApplicationBuilder builder) {
        // builder.listeners(new EnvironmentPreparedEventListener());
        return builder.sources(LargeDataScheduleApp.class);
    }

    /**
     * for JAR deploy
     */
    public static void main(String[] args) {
        configureSpringBuilder(new SpringApplicationBuilder())
                .run(args);
    }
}
