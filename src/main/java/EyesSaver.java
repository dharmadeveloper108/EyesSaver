import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.JBSplitter;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class EyesSaver extends AnAction {


    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        String message20mins = "<html>\n" +
                " You have looked at the screen for 20 minutes. <br /> Time to rest your eyes!" +
                "&nbsp; <a href=''" +
                ">OK I am ready</a>\n" +
                "</html>";

        String message2hrs = "<html>\n" +
                " You have looked at the screen for 2 hours. <br /> Take a 15 minute break!" +
                "</html>";

        show20minsMessage(message20mins);
        show2hrsMessage(message2hrs);

    }

    Timer timer = new Timer();
    Timer timer1 = new Timer();
    // every 20 minutes
    int timeInterval_20min = (1000 * 60) * 20;
    int begin_20min = timeInterval_20min;

    // every 2 hours
    int timeInterval_2hrs = ((1000 * 60) * 60) * 2;
    int begin_2hrs = timeInterval_2hrs;

    public static final NotificationGroup notification_group =
            new NotificationGroup("EyesSaver notification group",
                    NotificationDisplayType.STICKY_BALLOON, true);


    void show20minsMessage(String message) {
        ApplicationManager.getApplication().invokeLater(() -> timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Notification notification = notification_group.createNotification(message, "",
                        NotificationType.INFORMATION,
                        (notification1, event) -> {

                            fireDialog_20mins();

                        });
                Project[] projects = ProjectManager.getInstance().getOpenProjects();
                notification.setTitle("Eyes Saver");
                notification.setIcon(PluginIcons.EYEICON);

                notification.setContent(message);
                Notifications.Bus.notify(notification, projects[0]);
            }
        }, 0, 10000));


    }

    void show2hrsMessage(String message) {

        ApplicationManager.getApplication().invokeLater(() -> timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                Notification notification = notification_group.createNotification(message,
                        NotificationType.INFORMATION);
                Project[] projects = ProjectManager.getInstance().getOpenProjects();
                notification.setTitle("Eyes Saver");
                notification.setIcon(PluginIcons.EYEICON);

                notification.setContent(message);
                Notifications.Bus.notify(notification, projects[0]);
            }
        },begin_2hrs, timeInterval_2hrs));
    }

    void fireDialog_20mins() {
        NotificationDialog notificationDialog = new NotificationDialog();
        notificationDialog.show();
        //notificationDialog.doValidate();
    }

}
