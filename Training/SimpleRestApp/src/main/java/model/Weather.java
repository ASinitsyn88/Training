package model;

/**
 * Структура данных "Погода".
 * Описывает объект,возвращаемый веб-сервисом.
 */
public class Weather {

    // Дата прогноза
    private String date;
    // Описание погодных условий
    private String description;
    // Температура воздуха по Фаренгейту
    private String temperature;

    // Конструктор
    public Weather() {

    }

    /**
     * Получить дату прогноза
     * @return
     */
    public String getDate() {

        return date;
    }

    /**
     * Установить дату прогноза
     * @return
     */
    public void setDate(String date) {

        this.date = date;
    }

    /**
     * Получить описание погодных условий
     * @return
     */
    public String getDescription() {

        return description;
    }

    /**
     * Установить описание погодных условий
     * @param description
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * Получить температуру воздуха по Фаренгейту
     * @return
     */
    public String getTemperature() {

        return temperature;
    }

    /**
     * Установить температуру воздуха по Фаренгейту
     * @param temperature
     */
    public void setTemperature(String temperature) {

        this.temperature = temperature;
    }

    @Override
    public String toString() {

        return "Forecast{" +
                "date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", temperature='" + temperature + '\'' +
                '}';
    }
}
