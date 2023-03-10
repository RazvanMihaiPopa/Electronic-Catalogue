import java.util.ArrayList;

public class Catalog implements Subject {
    private static Catalog obj = null;
    ArrayList<Course> courses;
    private ArrayList<Observer> observers;
    private Catalog() {
        courses = new ArrayList<Course>();
        observers = new ArrayList<Observer>();
    }
    public static Catalog getInstance() {
        if (obj == null)
            obj = new Catalog();
        return obj;
    }
    public void addCourse(Course course) {
        Catalog.getInstance().courses.add(course);
    }
    public void removeCourse(Course course) {
        Catalog.getInstance().courses.remove(course);
    }
    public ArrayList<Observer> getObservers() {
        return observers;
    }
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(Observer observer) {
        if(observers.contains(observer))
            observers.remove(observer);
    }

    @Override
    public void notifyObservers(Grade grade) {
        Notification notification = new Notification(grade);
        for(Observer observer: observers) {
            if(grade.getStudent().getMother() != null && grade.getStudent().getMother().equals(observer))
                observer.update(notification);
            if(grade.getStudent().getFather() != null && grade.getStudent().getFather().equals(observer))
                observer.update(notification);
        }
    }
}
