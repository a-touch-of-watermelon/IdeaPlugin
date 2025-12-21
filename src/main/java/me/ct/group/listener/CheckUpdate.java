package me.ct.group.listener;

import com.intellij.ide.AppLifecycleListener;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.updateSettings.impl.UpdateChecker;
import com.intellij.openapi.updateSettings.impl.UpdateSettings;

/**
 * 检查IDE和插件的更新
 */
public class CheckUpdate implements AppLifecycleListener {

    private static final Logger LOG = Logger.getInstance(CheckUpdate.class);

    @Override
    public void welcomeScreenDisplayed() {
        UpdateSettings updateSettings = UpdateSettings.getInstance();
        
        UpdateSettings updateSettingsCopy = new UpdateSettings();//不改动用户设置
        updateSettingsCopy.getState().copyFrom(updateSettings.getState());
        updateSettingsCopy.setCheckNeeded(true);
        updateSettingsCopy.setPluginsCheckNeeded(true);
        
        UpdateChecker.updateAndShowResult(null, updateSettingsCopy);
        LOG.info("进行IDE和插件的检查更新-成功");
    }
    
}
