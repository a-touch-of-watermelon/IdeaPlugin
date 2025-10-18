package me.ct.group.util;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;

public class CommonUtil {

    public static void executeOnPooledThread(Runnable runnable) {
        Application application = ApplicationManager.getApplication();
        if (runnable != null) {
            application.executeOnPooledThread(runnable);
        }
    }

}
