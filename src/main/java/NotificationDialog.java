import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.TimerTask;
import javax.swing.Timer;


public class NotificationDialog extends DialogWrapper {
    private JProgressBar progressBar;
    public Timer timer;

    int k = 20;
    public NotificationDialog() {
        super(true); // use current window as parent
        init();
        setTitle("Eyes Saver");
        Timer t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                k--;
            }
        });
        t.start();
    }


    @Override
    protected @Nullable ValidationInfo doValidate() {
        return super.doValidate();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {

        progressBar = new JProgressBar(0, 20);
        progressBar.setStringPainted(true);
        progressBar.setValue(20);

        JPanel dialogPanel = new JPanel();
        String alertMessage = "Look 20 feet away from the screen for "+k+" seconds.";
        JLabel label = new JLabel(alertMessage);
        //label.setPreferredSize(new Dimension(500, 100));
        label.setIcon(PluginIcons.EYEICON);
        dialogPanel.add(label, BorderLayout.CENTER);
        dialogPanel.add(progressBar, BorderLayout.CENTER);
        return dialogPanel;

    }

    @NotNull
    @Override
    protected Action[] createActions() {
        pressEscKey();
        super.createDefaultActions();
        return new Action[] {
                getCancelAction()
        };
    }

    public void pressEscKey() {

        java.util.Timer timer = new java.util.Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Robot r = new Robot();
                    int keyCode = KeyEvent.VK_ESCAPE; // escape key
                    r.keyPress(keyCode);
                } catch (AWTException e){

                }
            }
        },20000);

    }
}
