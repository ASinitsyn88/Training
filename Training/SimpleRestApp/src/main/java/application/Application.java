package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"controller","service", "dao", "common"})
public class Application {

    /**
     * Точка входа в приложение
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        SpringApplication.run(Application.class, args);
    }
}
