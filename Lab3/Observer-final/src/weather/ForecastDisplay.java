package weather;

public class ForecastDisplay implements Observer, DisplayElement {
    @Override
    public void update(float temperature, float humidity, float pressure) {
        display();
    }

    @Override
    public void display() {
        System.out.println("Forecast weather~~");
    }
}
