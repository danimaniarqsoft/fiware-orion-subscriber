package mx.infotec.dads.orion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import mx.infotec.dads.orion.model.user.User;
import mx.infotec.dads.orion.repository.UserRepository;
import mx.infotec.dads.orion.util.QueryUtil;

@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties
public class MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }

    @Bean
    CommandLineRunner init(final UserRepository repository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... arg0) throws Exception {
                repository.deleteAll();
                User user = QueryUtil.createDefaulUserOne();
                repository.save(user);
                User userTwo = QueryUtil.createDefaulUserTwo();
                repository.save(userTwo);
                User userThree = QueryUtil.createDefaulUserThree();
                repository.save(userThree);
            }
        };
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

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserRepository usuarioRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = QueryUtil.createByExampleUser();
                user.setUsername(username);
                user = usuarioRepository.findOne(Example.of(user));
                if (user != null) {
                    return user;
                } else {
                    throw new UsernameNotFoundException("could not find the user '" + username + "'");
                }
            }
        };
    }
}

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/notifications","/logout").permitAll()
                .anyRequest().authenticated();
        
        http.authorizeRequests()
                .antMatchers("/app/**")
                .fullyAuthenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
