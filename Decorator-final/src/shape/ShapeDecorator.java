package shape;

public abstract class ShapeDecorator extends Shape {
    protected Shape decoratedShape; // Component reference

    protected ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

}
