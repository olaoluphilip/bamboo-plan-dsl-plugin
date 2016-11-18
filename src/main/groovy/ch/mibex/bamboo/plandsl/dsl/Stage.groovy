package ch.mibex.bamboo.plandsl.dsl

import ch.mibex.bamboo.plandsl.dsl.jobs.Job

class Stage extends BambooObject implements DslParent<Job> {
    String name
    String description
    boolean manual
    protected Set<Job> jobs = new LinkedHashSet<>()

    protected Stage(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // for testing
    protected Stage() {}

    /**
     * How do you want to identify the new plan stage?
     *
     * @param name the name of the stage
     */
    protected void name(String name) {
        this.name = name
    }

    /**
     * Choose a meaningful description for this plan stage.
     */
    void description(String description) {
        Validations.isSafeBambooString(description)
        this.description = description
    }

    /**
     * Specifies if the stage is manual or not. Manual stages require user interaction in order to execute.
     */
    void manual(boolean manual = true) {
        this.manual = manual
    }

    /**
     * Specifies a job for this stage. If the stage has multiple jobs, call this multiple times.
     *
     * @param key the key of the job consisting of an uppercase letter followed by one or more uppercase
     * alphanumeric characters. E. g. CORE (for a module called core)
     */
    Job job(String key, @DelegatesTo(Job) Closure closure) {
        def job = new Job(bambooFacade)
        job.key(key)
        DslScriptHelper.execute(closure, job)
        jobs << job
        job
    }

    Job job(String key, String name, @DelegatesTo(Job) Closure closure) {
        def job = new Job(bambooFacade)
        job.key(key)
        DslScriptHelper.execute(closure, job)
        jobs << job
        job
    }

    Job job(String key) {
        def job = new Job(bambooFacade)
        job.key(key)
        jobs << job
        job
    }

    /**
     * Specifies a job for this stage. If the stage has multiple jobs, call this multiple times.
     *
     * @param job a job object
     */
    Job job(Job job) {
        jobs << job
        job
    }

    @Override
    Collection<Job> children() {
        jobs
    }
}
