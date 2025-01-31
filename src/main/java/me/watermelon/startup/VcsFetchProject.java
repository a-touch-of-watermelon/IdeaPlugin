package me.watermelon.startup;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
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
import org.jetbrains.idea.svn.SvnVcs;

import java.util.Collection;

class VcsFetchProject {

    //https://plugins.jetbrains.com/docs/intellij/ide-infrastructure.html#logging
    public static final Logger LOG = Logger.getInstance(VcsFetchProject.class);

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
                LOG.info("Git自动刷新获取所有仓库远程变动-成功");
            };
            executeOnPooledThread(runnable);
        }

        if (svnFlag) {
            Runnable runnable = () -> {
                RefreshIncomingChangesAction.doRefresh(project);
                LOG.info("SVN自动刷新获取所有仓库远程变动-成功");
            };
            executeOnPooledThread(runnable);
        }
    }

    private static void executeOnPooledThread(Runnable runnable) {
        Application application = ApplicationManager.getApplication();
        if (runnable != null) {
            application.executeOnPooledThread(runnable);
        }
    }

}
