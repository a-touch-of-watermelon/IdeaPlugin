package me.ct.group.activity.vcs

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.vcs.ProjectLevelVcsManager
import me.ct.group.project.vcs.SVNFetch

class SVNProjectActivity : ProjectActivity {

    override suspend fun execute(project: Project) {
        ProjectLevelVcsManager.getInstance(project).runAfterInitialization {
            SVNFetch.execute(project)
        }
    }

}