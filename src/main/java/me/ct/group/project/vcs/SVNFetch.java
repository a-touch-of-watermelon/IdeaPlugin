package me.ct.group.project.vcs;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.AbstractVcs;
import com.intellij.openapi.vcs.ProjectLevelVcsManager;
import com.intellij.openapi.vcs.changes.committed.RefreshIncomingChangesAction;
import me.ct.group.setting.Setting;
import org.jetbrains.idea.svn.SvnVcs;

import java.util.Arrays;
import java.util.Objects;

/**
 * 获取所有项目仓库远程变动，目前仅支持git和svn
 */
public class SVNFetch {

    private static final Logger LOG = Logger.getInstance(SVNFetch.class);

    public static void execute(Project project) {
        Setting.State state = Objects.requireNonNull(Setting.getInstance().getState());
        if (!state.vscFetchStatus) {
            return;
        }

        ProjectLevelVcsManager projectLevelVcsManager = ProjectLevelVcsManager.getInstance(project);
        AbstractVcs[] allActiveVcs = projectLevelVcsManager.getAllActiveVcss();

        if (Arrays.stream(allActiveVcs).anyMatch(SvnVcs.class::isInstance)) {
            RefreshIncomingChangesAction.doRefresh(project);
            LOG.info("SVN获取所有仓库远程变动-成功");
        }
    }

}
