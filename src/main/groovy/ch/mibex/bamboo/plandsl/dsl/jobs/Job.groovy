package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.DslParent
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import ch.mibex.bamboo.plandsl.dsl.tasks.Task
import ch.mibex.bamboo.plandsl.dsl.tasks.Tasks

class Job extends BambooObject implements DslParent<Task> {
    String key
    String name
    String description
    boolean enabled = true
    Tasks tasksList = new Tasks()
    Artifacts artifacts = new Artifacts()

    Job(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // for testing:
    protected Job() {}

    /**
     * Creates a job definition.
     *
     * @param key the key of the job consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters. E. g. CORE (for a module called core)
     */
    void key(String key) {
        Validations.isNotNullOrEmpty(key, 'job key must be specified')
        Validations.isTrue(
                key ==~ /[A-Z][A-Z0-9]*/,
                'job key must consist of an uppercase letter followed by one or more uppercase alphanumeric characters.'
        )
        this.key = key
    }

    /**
     * Specifies the name of the job.
     */
    void name(String name) {
        Validations.isNotNullOrEmpty(name, 'job name must be specified')
        this.name = name
    }

    /**
     * Specifies the description of the job.
     */
    void description(String description) {
        Validations.isSafeBambooString(description)
        this.description = description
    }

    /**
     * Specifies if this job should be enabled or not.
     */
    void enabled(boolean enabled = true) {
        this.enabled = enabled
    }

    /**
     * Defines the artifact(s) for this job.
     */
    void artifacts(@DelegatesTo(Artifacts) Closure closure) {
        def artifacts = new Artifacts()
        DslScriptHelper.execute(closure, artifacts)
        this.artifacts = artifacts
    }

    /**
     * Defines the task(s) for this job.
     */
    void tasks(@DelegatesTo(Tasks) Closure closure) {
        def newTaskList = new Tasks()
        DslScriptHelper.execute(closure, newTaskList)
        tasksList = newTaskList
    }

    @Override
    Collection<Task> children() {
        tasksList.children()
    }
}
