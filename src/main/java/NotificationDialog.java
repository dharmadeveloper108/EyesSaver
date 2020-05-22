import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.Nullable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;


public class NotificationDialog extends DialogWrapper {
    private JProgressBar progressBar;


    public NotificationDialog() {
        super(true); // use current window as parent
        init();
        setTitle("Eyes Saver");
        //setOKButtonIcon(PluginIcons.EYEICON);
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

        JPanel dialogPanel = new JPanel(new BorderLayout());
        String alertMessage = "Look 20 feet away from the screen for seconds.";
        JLabel label = new JLabel(alertMessage);
        label.setPreferredSize(new Dimension(100, 100));
        label.setIcon(PluginIcons.EYEICON);
        dialogPanel.add(label, BorderLayout.CENTER);
        dialogPanel.add(progressBar);
        return dialogPanel;

    }

}
