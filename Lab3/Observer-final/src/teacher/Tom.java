package teacher;

public class Tom implements Student {

    @Override
    public void update(String message) {
        System.out.println("Tom received a message: " + message);
    }

}
