package shape;

public class FlowerDecorator extends ShapeDecorator {

    public FlowerDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public String getDescription() {
        return decoratedShape.getDescription() + " With Flower";
    }

}

