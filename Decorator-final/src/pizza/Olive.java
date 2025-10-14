package pizza;

public class Olive extends PizzaTopping {
    public Olive(IPizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olive";
    }

    @Override
    public double cost() {
        return pizza.cost() + 0.75;
    }
}
