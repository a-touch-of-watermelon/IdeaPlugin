package me.ct.group.project;

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

/**
 * 获取所有项目仓库远程变动，目前仅支持git和svn
 */
public class VcsFetch {

    //https://plugins.jetbrains.com/docs/intellij/ide-infrastructure.html#logging
    private static final Logger LOG = Logger.getInstance(VcsFetch.class);

    public static void execute(Project project) {
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
            GitFetchSupport gitFetchSupport = GitFetchSupport.fetchSupport(project);
            Collection<GitRepository> repositories = GitUtil.getRepositories(project);
            GitFetchResult gitFetchResult = gitFetchSupport.fetchAllRemotes(repositories);
            gitFetchResult.showNotification();
            LOG.info("Git获取所有仓库远程变动-成功");
        }

        if (svnFlag) {
            RefreshIncomingChangesAction.doRefresh(project);
            LOG.info("SVN获取所有仓库远程变动-成功");
        }
    }

}
