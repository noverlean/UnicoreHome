package unicore.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import unicore.api.utils.FreeMaker;

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

