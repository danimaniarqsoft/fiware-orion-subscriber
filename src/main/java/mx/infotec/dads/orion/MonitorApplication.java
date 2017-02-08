package mx.infotec.dads.orion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import mx.infotec.dads.orion.repository.NotificationRepository;

@SpringBootApplication
@EnableMongoRepositories
public class MonitorApplication implements CommandLineRunner {

    @Autowired
    private NotificationRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        repository.deleteAll();
    }

    /**
     * For more information about cors you can use
     * https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
     * 
     * @return WebMvcConfigurer
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowCredentials(true);
            config.addAllowedOrigin("http://localhost");
            config.addAllowedOrigin("http://consumer.dads.infotec.mx");
            config.addAllowedHeader("*");
            config.addAllowedMethod("*");
            source.registerCorsConfiguration("/**", config);
            FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
            bean.setOrder(0);
            return bean;
    }

}
