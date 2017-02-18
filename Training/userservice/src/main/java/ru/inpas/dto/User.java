package ru.inpas.dto;

import com.google.gson.Gson;
import lombok.*;

/**
 * Created by Alex on 15.02.2017.
 * Структура данных "Пользователь"
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class User {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String patronymic;

    @Getter
    @Setter
    private String phone;

    @Getter
    @Setter
    private String login;

    @Override
    public String toString() {

        return new Gson().toJson(this);
    }
}
