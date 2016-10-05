package ch.mibex.bamboo.plandsl.dsl.tasks

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString

class CleanWorkingDirTask extends Task {
    static final TASK_ID = 'com.atlassian.bamboo.plugins.bamboo-artifact-downloader-plugin:cleanWorkingDirectoryTask'

    CleanWorkingDirTask() {
        super(TASK_ID)
    }

    @Override
    Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config
    }
}