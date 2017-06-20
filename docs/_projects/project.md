---
title: Project
position: 1.0
right_code: |
  ~~~ groovy
  project(key: 'PROJECTKEY', name: 'my project') {
      plan(key: 'PLAN1KEY', name: 'my plan 1') {
      }
      plan(key: 'PLAN2KEY', name: 'my plan 2') {
      }
  }
  ~~~
  {: title="DSL" }
  ~~~ yml
  project:
    key: PROJECTKEY
    name: my project
    plans:
      - key: PLAN1KEY
        name: my plan 1
      - key: PLAN2KEY
        name: my plan 2
  ~~~
  {: title="YAML" }

---

A project contains of a collection of plans. Each project has a key (which must consist of an uppercase letter followed
by one or more uppercase alphanumeric characters), a name and its build plans.