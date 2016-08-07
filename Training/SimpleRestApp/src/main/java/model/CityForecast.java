package model;

import java.util.List;

/**
 * Структура данных "Прогноз погоды в каком-либо городе".
 * Описывает объект,возвращаемый веб-сервисом.
 */
public class CityForecast {

    // Штат/область
    private String state;
    // Город
    private String city;
    // Название метеостанции
    private String weatherStation;
    // Список прогнозов на близжайшие даты
    private List<Weather> weatherList;

    // Конструктор
    public CityForecast() {

    }

    /**
     * Получить штат/область
     * @return
     */
    public String getState() {

        return state;
    }

    /**
     * Установить штат/область
     * @param state
     */
    public void setState(String state) {

        this.state = state;
    }

    /**
     * Получить город
     * @return
     */
    public String getCity() {

        return city;
    }

    /**
     * Установить город
     * @param city
     */
    public void setCity(String city) {

        this.city = city;
    }

    /**
     * Получить название метеостанции
     * @return
     */
    public String getWeatherStation() {

        return weatherStation;
    }

    /**
     * Установить название метеостанции
     * @param weatherStation
     */
    public void setWeatherStation(String weatherStation) {

        this.weatherStation = weatherStation;
    }

    /**
     * Получить список прогнозов на близжайшие даты
     * @return
     */
    public List<Weather> getWeatherList() {

        return weatherList;
    }

    /**
     * Установить список прогнозов на близжайшие даты
     * @param weatherList
     */
    public void setWeatherList(List<Weather> weatherList) {

        this.weatherList = weatherList;
    }

    @Override
    public String toString() {

        return "CityForecast{" +
                "state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", weatherStation='" + weatherStation + '\'' +
                ", weatherList=" + weatherList +
                '}';
    }
}
