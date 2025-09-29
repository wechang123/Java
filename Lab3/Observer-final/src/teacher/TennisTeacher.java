package teacher;

import java.util.ArrayList;
import java.util.List;

public class TennisTeacher  implements Teacher {
    private List<Student> members = new ArrayList<>();
    
    public void play() {
        System.out.println("regular tennis play");
        notifyObservers("regular tennis play");
    }

    public void match() {
        System.out.println("tennis match!!");
        notifyObservers("tennis match!!");
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
