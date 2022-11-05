package com.myportfolio.base.model

/**
 * Enum class holding all the possible routes.
 * @property baseName This is the base name of the routes without any arguments. Mostly used to navigate to a route by adding the required arguments.
 * @property completeName This is the complete route's names with all the arguments definition. Mostly used by the Navigation component to define available routes.
 */
//sealed class Routes(
//    val baseName: String,
//    val arguments: List<RouteArgument> = listOf()
//) {
//    object MyProfile: Routes("profile")
//    object MyExperiences : Routes("experiences")
//    object MyProjects : Routes("projects")
//    object ExperienceDetail: Routes(
//        baseName = "experiences",
//        arguments = listOf(
//            RouteArgument("experience_id")
//        )
//    )
//    object ProjectDetail : Routes(
//        baseName = "projects",
//        arguments = listOf(
//            RouteArgument("project_id")
//        )
//    )
//
//    val completeName: String
//        get() = baseName + arguments.joinToString { "/{${it.name}}" }
//
//    fun navigate(vararg arguments: String): String {
//        return baseName + arguments.joinToString { "/$it" }
//    }
//
//    companion object {
//        fun Routes.getSingleArgName(): String {
//            return arguments.first().name
//        }
//    }
//}
enum class Routes(
    val baseName: String,
    val arguments: List<RouteArgument> = listOf()
) {
    MyProfile("profile"),
    MyExperiences("experiences"),
    MyProjects("projects"),
    ExperienceDetail(
        baseName = "experiences",
        arguments = listOf(
            RouteArgument("experience_id")
        )
    ),
    ProjectDetail(
        baseName = "projects",
        arguments = listOf(
            RouteArgument("project_id")
        )
    );

    val completeName: String
        get() = baseName + arguments.joinToString { "/{${it.name}}" }

    fun navigate(vararg arguments: String): String {
        return baseName + arguments.joinToString { "/$it" }
    }

    companion object {
        fun Routes.getSingleArgName(): String {
            return arguments.first().name
        }
    }
}

@JvmInline
value class RouteArgument(val name: String)