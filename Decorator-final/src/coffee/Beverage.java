package coffee;

public abstract class Beverage {
    String description = "Beverage";

    public String getDescription() {
        return this.description;
    }

    public abstract double cost();
}
