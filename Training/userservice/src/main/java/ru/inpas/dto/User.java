package ru.inpas.dto;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
