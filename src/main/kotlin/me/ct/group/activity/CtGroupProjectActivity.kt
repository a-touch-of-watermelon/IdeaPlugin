package me.ct.group.activity

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.vcs.ProjectLevelVcsManager
import me.ct.group.project.VcsFetch

/**
 * 每次打开项目会执行 [execute] 方法；
 * [project-open](https://plugins.jetbrains.com/docs/intellij/plugin-components.html#project-open),
 * Implementation in Kotlin is required because Java doesn't support suspending functions.
 */
class CtGroupProjectActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        ProjectLevelVcsManager.getInstance(project).runAfterInitialization {
            VcsFetch.execute(project)
        }
    }

}