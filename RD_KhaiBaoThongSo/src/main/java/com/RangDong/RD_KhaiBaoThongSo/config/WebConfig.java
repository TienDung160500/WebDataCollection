package com.RangDong.RD_KhaiBaoThongSo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
@Slf4j
@Configuration
public class WebConfig {
    @Bean // chứa CORS configuration
    public FilterRegistrationBean corsFilter(){
        log.info("FilterRegistrationBean");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        //cho  phép backend nhận header chứa thông tin xác thực
        config.setAllowCredentials(true);
        //chứa url của front-end
        config.addAllowedOrigin("http://localhost:4200");
        //các phần header đặc trưng mà ưn dụng phải chấp nhận
        config.setAllowedHeaders(Arrays.asList(
                HttpHeaders.AUTHORIZATION,
                HttpHeaders.CONTENT_TYPE,
                HttpHeaders.ACCEPT
        ));
        //các method ứng dụng phải chấp nhận
        config.setAllowedMethods(Arrays.asList(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()
        ));
        //set time the CORS config is accepted, 30p
        config.setMaxAge(3600L);
        // chấp nhận cấu hình cho tất cả routes
        source.registerCorsConfiguration("/**",config);
        // đặt bean ở vị trí thấp nhất để chạy đầu tiên sau khi khởi động server
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(-102);
        return bean;
    }
}
