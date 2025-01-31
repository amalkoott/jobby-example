pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FindJob"
include(":app")

include(":core")
include(":core:di")
include(":core:feature-api")
include(":core:domain")
include(":core:model")
//include(":core:model")

include(":feature")

include(":feature:login")
include(":feature:login:api")
include(":feature:login:impl")

include(":feature:search")
include(":feature:search:api")
include(":feature:search:impl")

include(":feature:favorites")
include(":feature:favorites:api")
include(":feature:favorites:impl")

include(":feature:response")
include(":feature:response:api")
include(":feature:response:impl")

include(":feature:message")
include(":feature:message:api")
include(":feature:message:impl")

include(":feature:profile")
include(":feature:profile:api")
include(":feature:profile:impl")

//include(":feature:api")
//include(":feature:api:login-api")
//include(":feature:api:search-api")
//include(":feature:api:favorites-i")
//include(":feature:api:message-api")
//include(":feature:api:profile-api")

//include(":feature:impl")
//include(":feature:impl:login-impl")
//include(":feature:impl:search-impl")
//include(":feature:impl:favorites-impl")

//include(":common")
//include(":navigation")
//include(":model")
//include(":domain")



//include(":mylibrary")

include(":core:common")
include(":core:network")
include(":core:database")
include(":feature:navigationbar")
