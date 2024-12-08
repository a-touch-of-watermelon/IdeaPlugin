package me.watermelon.startup

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import git4idea.GitUtil
import git4idea.fetch.GitFetchSupport

/**
 * 每次打开项目自动提取仓库远程变动 <br>
 * <a href="https://plugins.jetbrains.com/docs/intellij/plugin-components.html#project-open">project-open</a>,
 * Implementation in Kotlin is required because Java doesn't support suspending functions.
 */
class GitFetchProjectActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        val task = Runnable {
            val fetchSupport = GitFetchSupport.fetchSupport(project)
            val repositories = GitUtil.getRepositories(project)
            val gitFetchResult = fetchSupport.fetchAllRemotes(repositories)
            gitFetchResult.showNotification()
        }

        val application = ApplicationManager.getApplication()
        application.executeOnPooledThread(task)
    }
}