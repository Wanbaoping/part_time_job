package com.baoge.interceptor;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer/*WebMvcConfigurerAdapter*/ {
    //将jar文件下的对应静态资源文件路径对应到磁盘的路径(根据个人的情况修改"file:static/"的static的值
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ApplicationHome h = new ApplicationHome(getClass());
        File jarF = h.getSource();
        String dirPath = jarF.getParentFile().toString()+"/upload/";

        String os = System.getProperty("os.name");

        System.out.println("os"+os);

        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler("/upload/**").addResourceLocations("file:"+dirPath);
            System.out.println("file:" + dirPath);

        } else {  //linux 和mac
//            registry.addResourceHandler("/upload/**") //虚拟路劲
//                    .addResourceLocations("file:" + System.getProperty("user.dir") + "/upload/");//jar 同级目录
//            System.out.println("file:" + System.getProperty("user.dir") + "/upload/");
            registry.addResourceHandler("/upload/**").addResourceLocations("file:"+dirPath);
            System.out.println("file:" + dirPath);
        }
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
