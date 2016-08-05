package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Структура данных "Котировка".
 * Описывает объект,возвращаемый веб-сервисом.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

    // Статус получения котировки
    private String type;
    // Значение котировки
    private Value value;

    // Конструктор
    public Quote() {

    }

    /**
     * Получить статус получения котировки
     * @return
     */
    public String getType() {

        return type;
    }

    /**
     * Установить статус получения котировки
     * @param type
     */
    public void setType(String type) {

        this.type = type;
    }

    /**
     * Получить значение котировки
     * @return
     */
    public Value getValue() {

        return value;
    }

    /**
     * Установить значение котировки
     * @param value
     */
    public void setValue(Value value) {

        this.value = value;
    }

    @Override
    public String toString() {

        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
