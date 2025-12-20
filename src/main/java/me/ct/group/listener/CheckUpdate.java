package me.ct.group.listener;

import com.intellij.ide.AppLifecycleListener;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.updateSettings.impl.UpdateChecker;

/**
 * 检查IDE和插件的更新
 */
public class CheckUpdate implements AppLifecycleListener {

    private static final Logger LOG = Logger.getInstance(CheckUpdate.class);

    @Override
    public void welcomeScreenDisplayed() {
        UpdateChecker.updateAndShowResult(null);
        LOG.info("进行IDE和插件的检查更新-成功");
    }
    
}
