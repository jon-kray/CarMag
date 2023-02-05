package ru.ecosystem.carsale.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppConfiguration {

    @Value("${thread.pool.stream.size}")
    private int streamSize;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofBytes(100000000L));
        factory.setMaxRequestSize(DataSize.ofBytes(100000000L));
        return factory.createMultipartConfig();
    }

    @Bean
    @Primary
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(streamSize);
    }
}
