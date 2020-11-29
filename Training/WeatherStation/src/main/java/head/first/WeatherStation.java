package head.first;

import head.first.object.CurrentConditionsDisplay;
import head.first.subject.WeatherData;

/**
 * Паттерн "Наблюдатель"
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);

        // Имитация новых погодных данных
        weatherData.setMeasurments(80, 65, 30.4f);
        weatherData.setMeasurments(82, 70, 29.2f);
        weatherData.setMeasurments(78, 90, 29.2f);
    }
}
