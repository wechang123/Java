package shape;

public abstract class Shape {
    public void draw() {
        System.out.println("draw " + getDescription());
    }

    public abstract String getDescription();
}
