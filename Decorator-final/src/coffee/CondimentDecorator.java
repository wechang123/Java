package coffee;

public abstract class CondimentDecorator extends Beverage {
    protected Beverage beverage; // component reference
    public abstract String getDescription();
}
