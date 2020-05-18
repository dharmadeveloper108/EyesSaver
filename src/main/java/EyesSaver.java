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
import java.util.Timer;
import java.util.TimerTask;


public class EyesSaver extends AnAction {


    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        showMyMessage("Protect you eyes BYE");
    }

    Timer timer = new Timer();
    int begin = 0;
    int timeInterval = 10000;

    public static final NotificationGroup GROUP_DISPLAY_ID_INFO =
            new NotificationGroup("My notification group",
                    NotificationDisplayType.STICKY_BALLOON, true);


    void showMyMessage(String message) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {




                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Notification notification = GROUP_DISPLAY_ID_INFO.createNotification(message, NotificationType.INFORMATION);
                        Project[] projects = ProjectManager.getInstance().getOpenProjects();
                        notification.setTitle("Eyes Saver");
                        notification.setIcon(PluginIcons.EYEICON);
                        notification.addAction(new AnAction() {
                            @Override
                            public void actionPerformed(@NotNull AnActionEvent e) {
                                DialogBuilder dialogBuilder = new DialogBuilder();
                                dialogBuilder.setDimensionServiceKey("whatever");
                                dialogBuilder.setTitle("Hi");
                                dialogBuilder.setErrorText("Protect you eyes BYE");
                                dialogBuilder.removeAllActions();
                                dialogBuilder.addOkAction();
                                dialogBuilder.addCancelAction();

                                boolean isOk = dialogBuilder.show() == DialogWrapper.OK_EXIT_CODE;
                                if(isOk) {
                                    dialogBuilder.dispose();
                                }
                            }
                        });
                        Notifications.Bus.notify(notification, projects[0]);

                        //new NotificationDialog().showAndGet();

                    }
                }, begin, timeInterval);



            }
        });
    }


//    public void showDialog() {
//        DialogBuilder dialogBuilder = new DialogBuilder();
//        //dialogBuilder.setDimensionServiceKey("whatever");
//        dialogBuilder.setTitle("Hi");
//        dialogBuilder.setErrorText("Protect you eyes BYE");
//        dialogBuilder.removeAllActions();
//        dialogBuilder.addOkAction();
//        dialogBuilder.addCancelAction();
//
//        boolean isOk = dialogBuilder.show() == DialogWrapper.OK_EXIT_CODE;
//        if(isOk) {
//            dialogBuilder.dispose();
//        }
//    }

}
