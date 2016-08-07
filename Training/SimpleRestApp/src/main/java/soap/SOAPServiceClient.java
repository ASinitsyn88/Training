package soap;

import generated.weather.Forecast;
import generated.weather.ForecastReturn;
import generated.weather.GetCityForecastByZIP;
import generated.weather.GetCityForecastByZIPResponse;
import model.CityForecast;
import model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует вызов тестового SOAP-сервиса
 */
@Component
public class SOAPServiceClient extends WebServiceGatewaySupport {

    @Autowired
    private Jaxb2Marshaller marshaller;

    /**
     * Получить прогноз погоды по почтовому индексу
     * @param zipCode - почтовый индекс
     * @return
     */
    public CityForecast getCityForecastByZip(String zipCode) {

        // Объект запроса в веб-сервис
        GetCityForecastByZIP request = new GetCityForecastByZIP();
        request.setZIP(zipCode);

        // Объект ответа веб-сервиса
        WebServiceTemplate webServiceTemplate = getWebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        GetCityForecastByZIPResponse response = (GetCityForecastByZIPResponse) webServiceTemplate
                .marshalSendAndReceive(
                        "http://wsf.cdyne.com/WeatherWS/Weather.asmx",
                        request,
                        new SoapActionCallback("http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP"));

        // Бизнес-объекты,которые нужно вернуть клиенту
        CityForecast cityForecast = new CityForecast();
        List<Weather> weatherList = new ArrayList<>();

        ForecastReturn forecastReturn = response.getGetCityForecastByZIPResult();
        cityForecast.setState(forecastReturn.getState());
        cityForecast.setCity(forecastReturn.getCity());
        cityForecast.setWeatherStation(forecastReturn.getWeatherStationCity());

        List<Forecast> forecastList = forecastReturn.getForecastResult().getForecast();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (Forecast forecast : forecastList) {
            Weather weather = new Weather();
            weather.setDate(format.format(forecast.getDate().toGregorianCalendar().getTime()));
            weather.setDescription(forecast.getDesciption());
            weather.setTemperature(forecast.getTemperatures().getDaytimeHigh());
            weatherList.add(weather);
        }
        cityForecast.setWeatherList(weatherList);
        return cityForecast;
    }
}
