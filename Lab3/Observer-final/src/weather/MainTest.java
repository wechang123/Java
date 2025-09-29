package weather;

public class MainTest {
    public MainTest() {
        WeatherData weatherData = new WeatherData(); // Subject
        CurrentConditionDisplay o1 = new CurrentConditionDisplay(); // Observer1
        ForecastDisplay o2 = new ForecastDisplay(); // Observer2
        StatisticsDisplay o3 = new StatisticsDisplay(); // Observer3
        weatherData.registerObject(o1);
        System.out.println();
        weatherData.setMeasurements(80f, 65f, 30.4f); // o1
        weatherData.registerObject(o2);
        weatherData.registerObject(o3);
        System.out.println();
        weatherData.setMeasurements(82f, 70f, 29.2f); // o1,o2,o3
        System.out.println();
        weatherData.setMeasurements(78f, 90f, 29.2f); // o1,o2,o3
        weatherData.removeObject(o3);
        System.out.println();
        weatherData.setMeasurements(70f, 80f, 29.2f); // o1,o2
    }
}
