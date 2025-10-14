package coffee;

public class MainTest {
    public MainTest() {
        // Espresso
        Beverage b = new Espresso();
        System.out.println(b.getDescription() + " $" + b.cost());

        // DarkRoast + Mocha + Mocha + Whip
        b = new DarkRoast();
        b = new Mocha(b);
        b = new Mocha(b);
        b = new Whip(b);
        System.out.println(b.getDescription() + " $" + b.cost());

        // HouseBlend + Soy + Milk + Mocha + Whip
        b = new Whip(new Mocha(new Milk(new Soy(new HouseBlend()))));
        System.out.println(b.getDescription() + " $" + b.cost());
    }
}
