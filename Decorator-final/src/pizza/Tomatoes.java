package pizza;

public class Tomatoes extends PizzaTopping {
    public Tomatoes(IPizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Tomatoes";
    }

    @Override
    public double cost() {
        return pizza.cost() + 0.85;
    }
}
