package rest;

import model.Quote;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Класс реализует вызов фейкового REST-сервиса
 */
@Component
public class FakeRestService {

    public Quote getQuote() {

        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);

        return quote;
    }
}
