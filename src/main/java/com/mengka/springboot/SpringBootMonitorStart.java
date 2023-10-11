package com.mengka.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA
 * User: huangyy
 * Date: 2016/11/26
 * Time: 13:36
 */
@SpringBootApplication
@PropertySource("classpath:/properties/datasource.properties")
@ImportResource("classpath:/spring/applicationContext.xml")
public class SpringBootMonitorStart {

    public static void main(String[] args) {
//        System.setProperty("logging.path","/Users/xx1/logs/spring-boot33");

        String userHome = System.getProperty("user.home");
        System.out.println("-------, user.home: " + userHome);

        String logPath = System.getProperty("logging.path");
        System.out.println("-------, logging.path: " + logPath);

        SpringApplication.run(SpringBootMonitorStart.class, args);
    }

    /**
     * By default RestTemplate creates new Httpconnection every time and closes the connection once done.
     * <p>
     * If you need to have a connection pooling under rest template
     * then you may use different implementation of the ClientHttpRequestFactory that pools the connections.
     *
     * @return
     */
//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
//    }
}
