package weather.app.simpleweather;

public class Model {

    String weatherName, weatherVal;


    public Model(String wtext, String wName) {
        this.weatherName = wtext;
        this.weatherVal = wName;


    }


public String getWeatherName()
{
    return weatherName;
}



public String getWeatherval()
    {
        return weatherVal;
    }


}
