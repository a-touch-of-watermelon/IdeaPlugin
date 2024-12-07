package me.watermelon.actions;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

public class PopupDialogAction extends AnAction {

    @Override
    public void update(@NotNull AnActionEvent e) {
        // Set the availability based on whether a project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        //Using the event，create and show a dialog
        Project currentProject = e.getProject();
        StringBuilder message = new StringBuilder(e.getPresentation().getText()).append(" Selected！");
        //If an element is selected in the editor，add info about it。
        Navigatable selectedElement = e.getData(CommonDataKeys.NAVIGATABLE);
        if (selectedElement != null) {
            message.append("\nSelected Element：").append(selectedElement);
        }
        String title = e.getPresentation().getDescription();
        Messages.showMessageDialog(currentProject, message.toString(), title, Messages.getInformationIcon());
    }

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return super.getActionUpdateThread();
    }

}
