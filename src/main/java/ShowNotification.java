import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;

public class ShowNotification extends AnAction {
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
    
    
}
