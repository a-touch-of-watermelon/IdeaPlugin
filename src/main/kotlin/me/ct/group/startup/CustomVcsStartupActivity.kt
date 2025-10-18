package me.ct.group.startup

import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.impl.VcsInitObject
import com.intellij.openapi.vcs.impl.VcsStartupActivity

class CustomVcsStartupActivity : VcsStartupActivity {

    override suspend fun execute(project: Project) {
        VcsFetchProject.execute(project)
    }

    override val order: Int
        get() = VcsInitObject.AFTER_COMMON.order//确保仓库初始化完成后才执行
    
}