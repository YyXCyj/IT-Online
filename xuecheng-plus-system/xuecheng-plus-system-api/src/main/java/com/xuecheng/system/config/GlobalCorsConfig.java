package com.xuecheng.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author yy228
 * @version 1.0
 * @description TODO
 * @date 2023/1/30 17:42
 */

@Configuration
public class GlobalCorsConfig {

 /**
  * 允许跨域调用的过滤器
  */
 @Bean
 public CorsFilter corsFilter() {
  CorsConfiguration config = new CorsConfiguration();
  //允许白名单域名进行跨域调用
  config.addAllowedOrigin("*");
  //允许跨越发送cookie
  config.setAllowCredentials(true);
  //放行全部原始头信息
  config.addAllowedHeader("*");
  //允许所有请求方法跨域调用
  config.addAllowedMethod("*");
  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  source.registerCorsConfiguration("/**", config);
  return new CorsFilter(source);
 }
}
