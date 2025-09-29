package teacher;

import java.util.ArrayList;
import java.util.List;

public class SwimmingTeacher implements Teacher {
    private List<Student> members = new ArrayList<>();
    
    public void swim() {
        System.out.println("free swimming");
        notifyObservers("free swimming");
    }

    public void lesson() {
        System.out.println("swimming lesson");
        notifyObservers("swimming lesson");
    }

    @Override
    public void subscribeObserver(Student member) {
        members.add(member);
    }

    @Override
    public void unsubscribeObserver(Student member) {
        members.remove(member);
    }

    @Override
    public void notifyObservers(String message) {
        members.forEach(m -> m.update(message));
    }
}
