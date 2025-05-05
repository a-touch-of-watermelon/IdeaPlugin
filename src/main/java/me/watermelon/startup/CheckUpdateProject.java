package me.watermelon.startup;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.updateSettings.impl.UpdateChecker;
import me.watermelon.util.CommonUtil;

/**
 * 检查IDE和插件的更新
 */
class CheckUpdateProject {

    static void execute(Project project) {
        Runnable runnable = () -> UpdateChecker.updateAndShowResult(project);
        CommonUtil.executeOnPooledThread(runnable);
    }

}
