package me.ct.group.activity.vcs

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.vcs.ProjectLevelVcsManager
import me.ct.group.project.vcs.GitFetch

/**
 * 每次打开项目会执行 [execute] 方法；
 * [project-open](https://plugins.jetbrains.com/docs/intellij/plugin-components.html#project-open),
 * Implementation in Kotlin is required because Java doesn't support suspending functions.
 */
class GitProjectActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        ProjectLevelVcsManager.getInstance(project).runAfterInitialization {
            GitFetch.execute(project)
        }
    }

}