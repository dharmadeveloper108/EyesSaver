
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;

public class Countdown extends AnAction {

    Timer t = new Timer();
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

            }
        },0,1000);
    }
}
