package teacher;

public class Mitty implements Student {
    @Override
    public void update(String message) {
        System.out.println("Mitty received a message: " + message);
    }
}
