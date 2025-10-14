package pizza;

public class PlainPizza implements IPizza {

    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double cost() {
        return 5.00;
    }

}
