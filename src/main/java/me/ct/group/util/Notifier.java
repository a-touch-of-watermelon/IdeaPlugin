package me.ct.group.util;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;

public class Notifier {

    private static final String NOTIFICATION_GROUP_ID = "AutoSome Notification Group";

    public static void notifyInformation(Project project, String content) {
        notify(project, content, NotificationType.INFORMATION);
    }

    public static void notifyWarning(Project project, String content) {
        notify(project, content, NotificationType.WARNING);
    }

    private static void notify(Project project, String content, NotificationType notificationType) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup(NOTIFICATION_GROUP_ID)
                .createNotification(content, notificationType)
                .notify(project);
    }
    
}
