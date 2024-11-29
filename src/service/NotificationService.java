package service;

import repository.NotificationRepository;
import model.Notification;

import javax.swing.table.DefaultTableModel;

public class NotificationService {
    private NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationRepository getNotificationRepository() {
        return this.notificationRepository;
    }

    public void updateTable(DefaultTableModel notificationsTableModel) {
        notificationsTableModel.setRowCount(0);
        
        for (Notification notification : notificationRepository.getNotificationsList()) {
            String[] tableRow = {
                notification.getDate().toString(),
                notification.getDescription()
            };
            notificationsTableModel.addRow(tableRow);
        }

        notificationsTableModel.fireTableDataChanged();
    }
}
