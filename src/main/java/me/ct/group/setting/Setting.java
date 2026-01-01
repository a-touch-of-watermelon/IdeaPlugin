package me.ct.group.setting;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

@State(
        name = "me.ct.group.setting.Setting",
        storages = @Storage("AutoSomeSetting.xml")
)
public class Setting implements PersistentStateComponent<Setting.State> {

    public static class State {
        public boolean checkUpdateStatus = true;
        public boolean vscFetchStatus = true;
    }

    private State state = new State();

    public static Setting getInstance() {
        return ApplicationManager.getApplication().getService(Setting.class);
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
