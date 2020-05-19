import com.intellij.diagnostic.ReportMessages;
import com.intellij.ide.browsers.BrowserLauncher;
import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.newEditor.SettingsDialog;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.util.Timer;
import java.util.TimerTask;


public class EyesSaver extends AnAction {


    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        String message = "<html>\n" +
                " You have looked at the screen for two hours. Time to rest your eyes!" +
                "  <a href=''" +
                ">OK I am ready</a>\n" +
                "</html>";

        showMyMessage(message);

    }

    Timer timer = new Timer();
    int begin = 0;
    int timeInterval = 10000;

    public static final NotificationGroup notification_group =
            new NotificationGroup("My notification group",
                    NotificationDisplayType.STICKY_BALLOON, true);


    void showMyMessage(String message) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {



                        Notification notification = notification_group.createNotification(message, "",
                                NotificationType.INFORMATION,
                                new NotificationListener() {
                                    @Override
                                    public void hyperlinkUpdate(@NotNull Notification notification, @NotNull HyperlinkEvent event) {

                                        String alert = "Look away from the screen, ideally 20 feet away, for 20 seconds.";
                                        fireDialog(alert);

                                    }
                                });
                        Project[] projects = ProjectManager.getInstance().getOpenProjects();
                        notification.setTitle("Eyes Saver");
                        notification.setIcon(PluginIcons.EYEICON);

//                        ShowNotification showNotification = new ShowNotification();
//                        showNotification.displayTextInToolbar();
//
//                        notification.addAction(showNotification);
                        notification.setContent(message);
                        Notifications.Bus.notify(notification, projects[0]);

                        //new NotificationDialog().showAndGet();

                    }
                }, begin, timeInterval);



            }
        });
    }


    void fireDialog(String dialogMessage) {

        DialogBuilder dialogBuilder = new DialogBuilder();
        dialogBuilder.setDimensionServiceKey("whatever");
        dialogBuilder.setTitle("Eyes Saver");
        dialogBuilder.setErrorText(dialogMessage);
        dialogBuilder.removeAllActions();
        dialogBuilder.addOkAction();
        dialogBuilder.addCancelAction();

        boolean isOk = dialogBuilder.show() == DialogWrapper.OK_EXIT_CODE;
        if(isOk) {
            dialogBuilder.dispose();
        }
    }


}
