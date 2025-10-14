package shape;

public class MainTest {
    public MainTest() {
        // Circle
        Shape circle = new Circle();
        circle.draw();

        // Rectangle + RedColor + CheckPattern
        Shape rect = new Rectangle();
        rect = new ColorDecorator(rect, "Red");
        rect = new PatternDecorator(rect, "Check");
        rect.draw();

        // Triangle + Flower + RedColor + GreenColor + BlueColor
        Shape tri = new ColorDecorator(new ColorDecorator(new ColorDecorator(new FlowerDecorator(new Triangle()), "Red"), "Greeen"), "Blue");
        tri.draw();
    }
}
