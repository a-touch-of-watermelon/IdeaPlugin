package me.ct.group.setting;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.FormBuilder;
import me.ct.group.util.MessageBundle;

import javax.swing.*;

public class SettingUI {

    private final JPanel mainJPanel;
    
    private final JBCheckBox checkUpdateStatus = fixedWidthJBCheckBox(MessageBundle.message("setting.checkbox.checkUpdate"));
    
    private final JBCheckBox vscFetchStatus = fixedWidthJBCheckBox(MessageBundle.message("setting.checkbox.vscFetch"));

    public SettingUI() {
        mainJPanel = FormBuilder.createFormBuilder()
                .addComponent(checkUpdateStatus, 1)
                .addComponent(vscFetchStatus, 1)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    private JBCheckBox fixedWidthJBCheckBox(String text) {
        return new JBCheckBox("<html><body style='width: 430px'>" + text + "</body></html>");
    }

    public JPanel getPanel() {
        return mainJPanel;
    }

    public boolean getCheckUpdateStatus() {
        return checkUpdateStatus.isSelected();
    }

    public void setCheckUpdateStatus(boolean newStatus) {
        checkUpdateStatus.setSelected(newStatus);
    }

    public boolean getVscFetchStatus() {
        return vscFetchStatus.isSelected();
    }

    public void setVscFetchStatus(boolean newStatus) {
        vscFetchStatus.setSelected(newStatus);
    }
    
}
