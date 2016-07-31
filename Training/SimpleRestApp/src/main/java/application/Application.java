package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan({"controller","service", "dao", "common"})
public class Application extends SpringBootServletInitializer {

    /**
     * Переопределяем метод для возможности
     * деплоить war на сторонний сервер
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

        return application.sources(Application.class);
    }

    /**
     * Точка входа в приложение
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        SpringApplication.run(Application.class, args);
    }
}
