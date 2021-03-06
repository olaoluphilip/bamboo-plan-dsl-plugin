package ch.mibex.bamboo.plandsl.dsl.jobs

import ch.mibex.bamboo.plandsl.dsl.BambooFacade
import ch.mibex.bamboo.plandsl.dsl.BambooObject
import ch.mibex.bamboo.plandsl.dsl.DslScriptHelper
import ch.mibex.bamboo.plandsl.dsl.Validations
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includeFields=true, excludes = ['metaClass'])
@ToString(includeFields=true)
class Requirements extends BambooObject {
    private List<Requirement> requirements = []

    Requirements(BambooFacade bambooFacade) {
        super(bambooFacade)
    }

    // just for testing
    protected Requirements() {}

    /**
     * Defines a required capability.
     *
     * @param capabilityKey the key of the required capability, e.g. "system.builder.gradle.Gradle 2.2"
     * @param matchType the match type: "Equals", "Exists" or "Matches"
     */
    void requirement(String capabilityKey,
                     Requirement.MatchType matchType,
                     @DelegatesTo(value = Requirement, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        Validations.isTrue(
            ! requirements.find { it.capabilityKey == capabilityKey },
            'The requirement with this key already exists'
        )
        def requirement = new Requirement(capabilityKey, matchType, bambooFacade)
        DslScriptHelper.execute(closure, requirement)
        requirements << requirement
    }

    /**
     * Defines a required capability.
     *
     * @param params the mandatory parameters for a requirement definition.
     * Currently, "capabilityKey" and "matchType" are expected.
     */
    void requirement(Map<String, Object> params,
                     @DelegatesTo(value = Requirement, strategy = Closure.DELEGATE_FIRST) Closure closure) {
        requirement(params['capabilityKey'] as String, params['matchType'] as Requirement.MatchType, closure)
    }

    static Requirement.Equals equalsTo(String matchValue) {
        new Requirement.Equals(matchValue)
    }

    static Requirement.Exists exists() {
        new Requirement.Exists()
    }

    static Requirement.Matches matches(String regex) {
        new Requirement.Matches(regex)
    }
}
