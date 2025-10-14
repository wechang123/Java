package pizza;

public abstract class PizzaTopping implements IPizza {
    protected IPizza pizza; // component reference

    protected PizzaTopping(IPizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double cost() {
        return pizza.cost();
    }
}
