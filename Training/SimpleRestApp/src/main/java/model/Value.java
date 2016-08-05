package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Структура данных "Значение котировки".
 * Описывает объект,возвращаемый веб-сервисом.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

    // id котировки
    private Long id;
    // Текстовое описание котировки
    private String quote;

    // Конструктор
    public Value() {

    }

    /**
     * Получить id котировки
     */
    public Long getId() {

        return this.id;
    }

    /**
     * Установить id котировки
     * @param id
     */
    public void setId(Long id) {

        this.id = id;
    }

    /**
     * Получить текстовое описание котировки
     * @return
     */
    public String getQuote() {

        return this.quote;
    }

    /**
     * Установить текстовое описание котировки
     * @param quote
     */
    public void setQuote(String quote) {

        this.quote = quote;
    }

    @Override
    public String toString() {

        return "Value{" +
                "id=" + id +
                ", quote='" + quote + '\'' +
                '}';
    }
}
