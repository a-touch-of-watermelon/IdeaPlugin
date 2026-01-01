package me.ct.group.setting;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

@State(
        name = "me.ct.group.setting.PluginSetting",
        storages = @Storage("AutoSomeSettingsPlugin.xml")
)
public class PluginSetting implements PersistentStateComponent<PluginSetting.State> {

    public static class State {
        public boolean checkUpdateStatus = true;
        public boolean vscFetchStatus = true;
    }

    private State state = new State();

    public static PluginSetting getInstance() {
        return ApplicationManager.getApplication().getService(PluginSetting.class);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull State state) {
        this.state = state;
    }

}
