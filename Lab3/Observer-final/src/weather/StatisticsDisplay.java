package weather;

import java.util.ArrayList;
import java.util.List;

public class StatisticsDisplay implements Observer, DisplayElement {
    private float minTemperature = 999;
    private float maxTemperature = -999;
    private float avgTemperature = 0;
    private List<Float> tempList = new ArrayList<>();

    @Override
    public void update(float temperature, float humidity, float pressure) {
        tempList.add(temperature);
        display();
    }

    @Override
    public void display() {
        float sum = 0;
        for (float t : tempList) {
            if (t < minTemperature) minTemperature = t;
            if (t > maxTemperature) maxTemperature = t;
            sum += t;
        }
        avgTemperature = sum/(int)tempList.size();
        System.out.println("Statistics weather: minTemp = "
         + minTemperature + " maxTemp = " + maxTemperature 
         + " avgTemp = " + avgTemperature);
    }
}
