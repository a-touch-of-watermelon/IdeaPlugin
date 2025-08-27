package me.watermelon.startup

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

/**
 * 每次打开项目会执行 [execute] 方法；
 * [project-open](https://plugins.jetbrains.com/docs/intellij/plugin-components.html#project-open),
 * Implementation in Kotlin is required because Java doesn't support suspending functions.
 */
class CustomProjectActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        CheckUpdateProject.execute(project)
    }

}