package rest;

import model.Quote;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Класс реализует вызов тестового REST-сервиса
 */
@Component
public class RestServiceClient {

    private static final String URL = "http://gturnquist-quoters.cfapps.io/api/random";

    public Quote getQuote() {

        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject(URL, Quote.class);

        return quote;
    }
}
