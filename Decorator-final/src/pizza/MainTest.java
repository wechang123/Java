package pizza;

public class MainTest {
    public MainTest() {
        // PlainPizza + Pepperoni + Tomatoes + Mushroom + Olive + Cheese
        IPizza pizza = new PlainPizza();
        pizza = new Pepperoni(pizza);
        pizza = new Tomatoes(pizza);
        pizza = new Mushroom(pizza);
        pizza = new Olive(pizza);
        pizza = new Cheese(pizza);
        System.out.println();
        System.out.println(pizza.getDescription() + " $" + pizza.cost());
    }
}
