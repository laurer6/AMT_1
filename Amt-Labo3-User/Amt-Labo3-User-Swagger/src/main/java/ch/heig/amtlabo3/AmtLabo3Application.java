package ch.heig.amtlabo3;

import ch.heig.amtlabo3.api.filters.JwtRequestFilter;
import ch.heig.amtlabo3.api.util.JwtUtil;
import ch.heig.amtlabo3.configuration.InitDataForAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.ExitCodeGenerator;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = { "ch.heig.amtlabo3", "ch.heig.amtlabo3.api" })
public class AmtLabo3Application implements CommandLineRunner {

    @Autowired
    private InitDataForAPI initDataForAPI;

    @Autowired
    private JwtRequestFilter authFilter;

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    @Bean
    public FilterRegistrationBean< JwtRequestFilter > filterRegistrationBean() {
        FilterRegistrationBean<JwtRequestFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(authFilter);
        registrationBean.addUrlPatterns("/users/*");
        return registrationBean;
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(AmtLabo3Application.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }
    }

    @PostConstruct
    void init() {
        initDataForAPI.generateDataForUsers();
    }
}