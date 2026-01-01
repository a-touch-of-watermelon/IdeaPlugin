package me.ct.group.setting;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

public class SettingConfig implements Configurable {
    
    private SettingUI settingUI;

    // A default constructor with no arguments is required because
    // this implementation is registered as an applicationConfigurable

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Auto Some";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingUI = new SettingUI();
        return settingUI.getPanel();
    }

    @Override
    public boolean isModified() {
        Setting.State state = Objects.requireNonNull(Setting.getInstance().getState());
        return settingUI.getVscFetchStatus() != state.vscFetchStatus ||
                settingUI.getCheckUpdateStatus() != state.checkUpdateStatus;
    }

    @Override
    public void apply() {
        Setting.State state = Objects.requireNonNull(Setting.getInstance().getState());
        state.vscFetchStatus = settingUI.getVscFetchStatus();
        state.checkUpdateStatus = settingUI.getCheckUpdateStatus();
    }

    @Override
    public void reset() {
        Setting.State state = Objects.requireNonNull(Setting.getInstance().getState());
        settingUI.setVscFetchStatus(state.vscFetchStatus);
        settingUI.setCheckUpdateStatus(state.checkUpdateStatus);
    }

    @Override
    public void disposeUIResources() {
        settingUI = null;
    }

}
