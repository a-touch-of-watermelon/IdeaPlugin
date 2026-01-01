package me.ct.group.listener;

import com.intellij.ide.AppLifecycleListener;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.updateSettings.impl.UpdateChecker;
import com.intellij.openapi.updateSettings.impl.UpdateSettings;
import com.intellij.util.text.DateFormatUtil;
import me.ct.group.setting.Setting;

import java.util.Date;
import java.util.Objects;

/**
 * 检查IDE和插件的更新
 */
public class CheckUpdate implements AppLifecycleListener {

    private static final Logger LOG = Logger.getInstance(CheckUpdate.class);
    
    private static final String LAST_CHECK_DATE_KEY = "me.ct.group.check.update.last.check.date";

    @Override
    public void welcomeScreenDisplayed() {
        Setting.State state = Objects.requireNonNull(Setting.getInstance().getState());
        if (!state.checkUpdateStatus) {
            return;
        }
        
        String checkDate = DateFormatUtil.formatDate(new Date());
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        String lastCheckDate = propertiesComponent.getValue(LAST_CHECK_DATE_KEY);
        if (checkDate.equals(lastCheckDate)) {
            LOG.info("今天已经进行IDE和插件的检查更新，跳过检查");
            return;
        }

        UpdateSettings updateSettings = UpdateSettings.getInstance();

        UpdateSettings updateSettingsCopy = new UpdateSettings();//不改动用户设置
        updateSettingsCopy.getState().copyFrom(updateSettings.getState());
        updateSettingsCopy.setCheckNeeded(true);
        updateSettingsCopy.setPluginsCheckNeeded(true);

        UpdateChecker.updateAndShowResult(null, updateSettingsCopy);

        propertiesComponent.setValue(LAST_CHECK_DATE_KEY, checkDate);
        LOG.info("进行IDE和插件的检查更新-成功");
    }

}
