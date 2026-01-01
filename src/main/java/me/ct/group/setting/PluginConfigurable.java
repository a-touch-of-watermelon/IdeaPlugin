package me.ct.group.setting;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

public class PluginConfigurable implements Configurable {
    
    private PluginSettingsComponent pluginSettingsComponent;

    // A default constructor with no arguments is required because
    // this implementation is registered as an applicationConfigurable

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Auto Some";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return pluginSettingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        pluginSettingsComponent = new PluginSettingsComponent();
        return pluginSettingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        PluginSetting.State state = Objects.requireNonNull(PluginSetting.getInstance().getState());
        return pluginSettingsComponent.getVscFetchStatus() != state.vscFetchStatus ||
                pluginSettingsComponent.getCheckUpdateStatus() != state.checkUpdateStatus;
    }

    @Override
    public void apply() {
        PluginSetting.State state = Objects.requireNonNull(PluginSetting.getInstance().getState());
        state.vscFetchStatus = pluginSettingsComponent.getVscFetchStatus();
        state.checkUpdateStatus = pluginSettingsComponent.getCheckUpdateStatus();
    }

    @Override
    public void reset() {
        PluginSetting.State state = Objects.requireNonNull(PluginSetting.getInstance().getState());
        pluginSettingsComponent.setVscFetchStatus(state.vscFetchStatus);
        pluginSettingsComponent.setCheckUpdateStatus(state.checkUpdateStatus);
    }

    @Override
    public void disposeUIResources() {
        pluginSettingsComponent = null;
    }

}
