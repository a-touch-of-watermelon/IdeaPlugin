package me.ct.group.util;

import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.extensions.PluginId;

public class CommonUtil {

    public static boolean isPluginEnabled(String pluginId) {
        PluginId id = PluginId.getId(pluginId);
        return !PluginManagerCore.isDisabled(id);
    }
    
}
