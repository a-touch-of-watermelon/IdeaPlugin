package me.ct.group.startup;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.updateSettings.impl.UpdateChecker;
import me.ct.group.util.CommonUtil;

/**
 * 检查IDE和插件的更新
 */
class CheckUpdateProject {

    private static final Logger LOG = Logger.getInstance(CheckUpdateProject.class);

    /**
     * 开启IDE后，是否是第一次打开项目；
     * <li>IDE首次被打开，此静态变量随着此类class实例被加载而赋值为 {@code true}，执行检查更新后，此静态变量被赋值为 {@code false}
     * （无其他赋值可能，TODO 除非此类class实例被JVM释放了后又进行了加载）
     * <li>在IDE被关闭（所有资源都会被释放）后再打开，就会重复上面的逻辑
     */
    private static Boolean firstFlag = true;

    static void execute(Project project) {
        if (firstFlag) {
            LOG.info("开启IDE后，第一次打开项目，进行IDE和插件的检查更新");
            Runnable runnable = () -> UpdateChecker.updateAndShowResult(project);
            CommonUtil.executeOnPooledThread(runnable);
            firstFlag = false;
        } else {
            LOG.info("开启IDE后，非第一次打开项目，不进行IDE和插件的检查更新");
        }
    }

}
