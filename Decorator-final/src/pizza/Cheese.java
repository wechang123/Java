package pizza;

public class Cheese extends PizzaTopping {
    public Cheese(IPizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    @Override
    public double cost() {
        return pizza.cost() + 1.5;
    }
}
