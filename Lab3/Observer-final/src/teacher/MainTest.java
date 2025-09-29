package teacher;

public class MainTest {
    public MainTest() {
        SwimmingTeacher swimmingTeacher = new SwimmingTeacher();
        TennisTeacher tennisTeacher = new TennisTeacher();
        Student tom = new Tom();
        Student jerry = new Jerry();
        Student mitty = new Mitty();

        swimmingTeacher.subscribeObserver(tom);
        swimmingTeacher.subscribeObserver(jerry);
        swimmingTeacher.subscribeObserver(mitty);
        System.out.println();
        swimmingTeacher.lesson(); // tom, jerry, mitty
        System.out.println();
        swimmingTeacher.swim(); // tom, jerry, mitty

        tennisTeacher.subscribeObserver(tom);
        tennisTeacher.subscribeObserver(jerry);
        System.out.println();
        tennisTeacher.play(); // tom, jerry
        System.out.println();
        tennisTeacher.unsubscribeObserver(tom);
        tennisTeacher.match(); // jerry
    }
}
