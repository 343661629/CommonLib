pluginManagement {
    repositories {
//        maven { url = uri("https://maven.google.com") }
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
//        maven { url = uri("https://maven.google.com") }
        google()
        mavenCentral()
    }
}

rootProject.name = "CommonLibProject"
include(":app")
include(":NetWorkLibrary")
