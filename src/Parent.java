import java.util.ArrayList;

public class Parent extends User implements Observer {
    ArrayList<Notification> notifications = new ArrayList<Notification>();
    public Parent(String firstName, String lastName) {
        super(firstName, lastName);
    }
    @Override
    public void update(Notification notification) {
        notifications.add(notification);
        System.out.println(notification.toString());
    }
}
