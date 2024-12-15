package me.watermelon.startup;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import git4idea.GitUtil;
import git4idea.fetch.GitFetchResult;
import git4idea.fetch.GitFetchSupport;
import git4idea.repo.GitRepository;

import java.util.Collection;

class GitFetchProject {

    //https://plugins.jetbrains.com/docs/intellij/ide-infrastructure.html#logging
    public static final Logger LOG = Logger.getInstance(GitFetchProject.class);

    static void execute(Project project) {
        Runnable runnable = () -> {
            GitFetchSupport gitFetchSupport = GitFetchSupport.fetchSupport(project);
            Collection<GitRepository> repositories = GitUtil.getRepositories(project);
            GitFetchResult gitFetchResult = gitFetchSupport.fetchAllRemotes(repositories);
            gitFetchResult.showNotification();
            LOG.info("自动提取仓库远程变动-成功");
        };

        Application application = ApplicationManager.getApplication();
        application.executeOnPooledThread(runnable);
    }

}
