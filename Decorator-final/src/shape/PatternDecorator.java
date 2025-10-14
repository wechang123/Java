package shape;

public class PatternDecorator extends ShapeDecorator {
    private String pattern;

    public PatternDecorator(Shape decoratedShape, String pattern) {
        super(decoratedShape);
        this.pattern = pattern;
    }

    @Override
    public String getDescription() {
        return decoratedShape.getDescription() + " With " + pattern + " pattern";
    }

}
