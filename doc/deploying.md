# How to deploy a new version

* Update `CHANGELOG.md` file
* Create a branch named `release/<manifest-version>`
* In this branch, remove the `-SNAPSHOT` postfix from the `version` field in `gradle.build`
* From this branch, run `./gradlew jar`
* In TestProject, replace the addon version with the jar generated in `/build/libs`
* Checkout `main` branch and update the `gradle.build`'s `version` field to a higher number
