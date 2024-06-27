package unicore.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.CharacterEncodingFilter;
import unicore.api.utils.FreeMaker;

import java.util.Collections;

@SpringBootApplication
public class BasicApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
        new FreeMaker();
    }

//    @Bean
//    public FilterRegistrationBean<CharacterEncodingFilter> characterEncodingFilter() {
//        FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new CharacterEncodingFilter());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setInitParameters(Collections.singletonMap("encoding", "UTF-8"));
//        registrationBean.setName("characterEncodingFilter");
//        return registrationBean;
//    }
}

