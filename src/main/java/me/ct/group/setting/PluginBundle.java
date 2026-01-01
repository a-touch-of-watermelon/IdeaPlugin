package me.ct.group.setting;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

import java.util.function.Supplier;

public class PluginBundle {

    @NonNls
    private static final String BUNDLE = "messages.PluginBundle";

    private static final DynamicBundle DYNAMIC_BUNDLE = new DynamicBundle(PluginBundle.class, BUNDLE);

    public static @NotNull @Nls String message(
            @NotNull @PropertyKey(resourceBundle = BUNDLE) String key,
            Object @NotNull ... params
    ) {
        return DYNAMIC_BUNDLE.getMessage(key, params);
    }

}
