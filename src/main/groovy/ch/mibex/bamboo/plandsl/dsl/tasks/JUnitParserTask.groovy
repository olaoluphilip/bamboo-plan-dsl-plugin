package ch.mibex.bamboo.plandsl.dsl.tasks

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString

class JUnitParserTask extends Task {
    static final TASK_ID = 'com.atlassian.bamboo.plugins.testresultparser:task.testresultparser.junit'
    String testResultsDirectory
    boolean pickUpTestResultsCreatedOutsideOfBuild

    JUnitParserTask() {
        super(TASK_ID)
    }

    void testResultsDirectory(String testResultsDirectory) {
        this.testResultsDirectory = testResultsDirectory
    }

    void pickUpTestResultsCreatedOutsideOfBuild(boolean pickUpTestResultsCreatedOutsideOfBuild) {
        this.pickUpTestResultsCreatedOutsideOfBuild = pickUpTestResultsCreatedOutsideOfBuild
    }

    @Override
    def Map<String, String> getConfig(Map<Object, Object> context) {
        def config = [:]
        config.put('testResultsDirectory', testResultsDirectory)
        config.put('pickUpTestResultsCreatedOutsideOfBuild', pickUpTestResultsCreatedOutsideOfBuild)
        config
    }
}