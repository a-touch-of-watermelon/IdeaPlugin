package me.ct.group.util;

import com.intellij.DynamicBundle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.PropertyKey;

public class MessageBundle {

    @NonNls
    private static final String BUNDLE = "messages.Bundle";

    private static final DynamicBundle DYNAMIC_BUNDLE = new DynamicBundle(MessageBundle.class, BUNDLE);

    public static @NotNull @Nls String message(
            @NotNull @PropertyKey(resourceBundle = BUNDLE) String key,
            Object @NotNull ... params
    ) {
        return DYNAMIC_BUNDLE.getMessage(key, params);
    }

}
