package me.ct.group.project.vcs;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.AbstractVcs;
import com.intellij.openapi.vcs.ProjectLevelVcsManager;
import git4idea.GitUtil;
import git4idea.GitVcs;
import git4idea.fetch.GitFetchResult;
import git4idea.fetch.GitFetchSupport;
import git4idea.repo.GitRepository;
import me.ct.group.setting.Setting;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * 获取所有项目仓库远程变动，目前仅支持git和svn
 */
public class GitFetch {

    //https://plugins.jetbrains.com/docs/intellij/ide-infrastructure.html#logging
    private static final Logger LOG = Logger.getInstance(GitFetch.class);

    public static void execute(Project project) {
        Setting.State state = Objects.requireNonNull(Setting.getInstance().getState());
        if (!state.vscFetchStatus) {
            return;
        }

        ProjectLevelVcsManager projectLevelVcsManager = ProjectLevelVcsManager.getInstance(project);
        AbstractVcs[] allActiveVcs = projectLevelVcsManager.getAllActiveVcss();

        if (Arrays.stream(allActiveVcs).anyMatch(GitVcs.class::isInstance)) {
            GitFetchSupport gitFetchSupport = GitFetchSupport.fetchSupport(project);
            Collection<GitRepository> repositories = GitUtil.getRepositories(project);
            GitFetchResult gitFetchResult = gitFetchSupport.fetchAllRemotes(repositories);
            gitFetchResult.showNotification();
            LOG.info("Git获取所有仓库远程变动-成功");
        }
    }

}
