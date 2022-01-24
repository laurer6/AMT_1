package ch.heig.amt;

import ch.heig.amt.configuration.InitDataForAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = { "ch.heig.amt", "ch.heig.amt.api" })
public class Swagger2SpringBoot implements CommandLineRunner {

    @Autowired
    private InitDataForAPI initDataForAPI;

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Swagger2SpringBoot.class).run(args);
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
        initDataForAPI.generateDataForStations();
        initDataForAPI.generateDataForPrice();
        initDataForAPI.generateDataForVehicle();
        initDataForAPI.generateDataForEmplacement();
        initDataForAPI.generateDataForReservation();
    }
}
