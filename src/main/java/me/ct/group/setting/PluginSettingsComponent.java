package me.ct.group.setting;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.util.ui.FormBuilder;

import javax.swing.*;

public class PluginSettingsComponent {

    private final JPanel mainJPanel;
    
    private final JBCheckBox checkUpdateStatus = new JBCheckBox(PluginBundle.message("setting.checkbox.checkUpdate"));
    
    private final JBCheckBox vscFetchStatus = new JBCheckBox(PluginBundle.message("setting.checkbox.vscFetch"));

    public PluginSettingsComponent() {
        mainJPanel = FormBuilder.createFormBuilder()
                .addComponent(checkUpdateStatus, 1)
                .addComponent(vscFetchStatus, 1)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return mainJPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return checkUpdateStatus;
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
