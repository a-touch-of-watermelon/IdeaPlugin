package me.watermelon.startup;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.AbstractVcs;
import com.intellij.openapi.vcs.ProjectLevelVcsManager;
import com.intellij.openapi.vcs.changes.committed.RefreshIncomingChangesAction;
import git4idea.GitUtil;
import git4idea.GitVcs;
import git4idea.fetch.GitFetchResult;
import git4idea.fetch.GitFetchSupport;
import git4idea.repo.GitRepository;
import me.watermelon.util.CommonUtil;
import org.apache.commons.lang3.ThreadUtils;
import org.jetbrains.idea.svn.SvnVcs;

import java.time.Duration;
import java.util.Collection;

/**
 * 获取所有仓库远程变动，目前仅支持git和svn
 */
class VcsFetchProject {

    //https://plugins.jetbrains.com/docs/intellij/ide-infrastructure.html#logging
    private static final Logger LOG = Logger.getInstance(VcsFetchProject.class);

    static void execute(Project project) {
        boolean gitFlag = false;
        boolean svnFlag = false;
        ProjectLevelVcsManager projectLevelVcsManager = ProjectLevelVcsManager.getInstance(project);
        AbstractVcs[] allActiveVcs = projectLevelVcsManager.getAllActiveVcss();
        for (AbstractVcs activeVcs : allActiveVcs) {
            if (activeVcs instanceof GitVcs) {
                gitFlag = true;
            } else if (activeVcs instanceof SvnVcs) {
                svnFlag = true;
            }
        }

        if (gitFlag) {
            Runnable runnable = () -> {
                GitFetchSupport gitFetchSupport = GitFetchSupport.fetchSupport(project);
                Collection<GitRepository> repositories = GitUtil.getRepositories(project);
                GitFetchResult gitFetchResult = gitFetchSupport.fetchAllRemotes(repositories);
                gitFetchResult.showNotification();
                LOG.info("Git获取所有仓库远程变动-成功");
            };
            CommonUtil.executeOnPooledThread(runnable);
        }

        if (svnFlag) {
            Runnable runnable = () -> {
                RefreshIncomingChangesAction.doRefresh(project);
                LOG.info("SVN获取所有仓库远程变动-成功");
            };
            CommonUtil.executeOnPooledThread(runnable);
        }
    }

}
