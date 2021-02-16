package weather.app.simpleweather;

public class Model {

    private String WeatherName;
    private Double WeatherVal;

public Model()
{}

    public String getWeatherName() {
        return WeatherName;
    }

    public void setWeatherName(String weatherName) {
        this.WeatherName = weatherName;
    }

    public Double getWeatherVal() {
        return WeatherVal;
    }

    public void setWeatherVal(Double weatherVal) {
        this.WeatherVal = weatherVal;
    }
}
