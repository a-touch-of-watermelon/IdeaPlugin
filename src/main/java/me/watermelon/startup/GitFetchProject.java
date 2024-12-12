package me.watermelon.startup;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import git4idea.GitUtil;
import git4idea.fetch.GitFetchResult;
import git4idea.fetch.GitFetchSupport;
import git4idea.repo.GitRepository;

import java.util.Collection;

class GitFetchProject {

    static void execute(Project project) {
        Runnable runnable = () -> {
            GitFetchSupport gitFetchSupport = GitFetchSupport.fetchSupport(project);
            Collection<GitRepository> repositories = GitUtil.getRepositories(project);
            GitFetchResult gitFetchResult = gitFetchSupport.fetchAllRemotes(repositories);
            gitFetchResult.showNotification();
        };

        Application application = ApplicationManager.getApplication();
        application.executeOnPooledThread(runnable);
    }

}
