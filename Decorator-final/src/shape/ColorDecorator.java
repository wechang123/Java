package shape;

public class ColorDecorator extends ShapeDecorator {
    private String color;

    public ColorDecorator(Shape decoratedShape, String color) {
        super(decoratedShape);
        this.color = color;
    }

    @Override
    public String getDescription() {
        return decoratedShape.getDescription() + " With " + color + " color";
    }

}
