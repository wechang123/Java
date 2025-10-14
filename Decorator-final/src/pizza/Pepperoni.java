package pizza;

public class Pepperoni extends PizzaTopping {
    public Pepperoni(IPizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Pepperoni";
    }

    @Override
    public double cost() {
        return pizza.cost() + 2.0;
    }
}
