package repository;

import java.util.List;
import java.util.ArrayList;

import model.Notification;

public class NotificationRepository {
    private List<Notification> notificationsList = new ArrayList<>();

    public void addNotification(Notification notificationToAdd) {
        notificationsList.add(notificationToAdd);
    }

    public void removeNotification(Notification NotificationToRemove) {
        notificationsList.remove(NotificationToRemove);
    }

    public List<Notification> getNotificationsList() {
        return new ArrayList<>(notificationsList);
    }
}