package me.watermelon.startup

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

/**
 * 每次打开项目自动刷新获取仓库远程变动，目前仅支持git和svn
 *
 * [project-open](https://plugins.jetbrains.com/docs/intellij/plugin-components.html#project-open),
 * Implementation in Kotlin is required because Java doesn't support suspending functions.
 */
class VcsFetchProjectActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        VcsFetchProject.execute(project)
    }
}