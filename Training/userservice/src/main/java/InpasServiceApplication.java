import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Alex on 15.02.2017.
 * Точка входа в приложение
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableWebMvc
@EntityScan("ru.inpas.*")
@ComponentScan("ru.inpas.*")
@EnableAutoConfiguration
public class InpasServiceApplication extends SpringBootServletInitializer {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(InpasServiceApplication.class, args);
    }
}
