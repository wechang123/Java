package pizza;

public class Mushroom extends PizzaTopping {
    public Mushroom(IPizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Mushroom";
    }

    @Override
    public double cost() {
        return pizza.cost() + 1.25;
    }
}
