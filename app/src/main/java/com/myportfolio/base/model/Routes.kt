package com.myportfolio.base.model

import androidx.navigation.NavType
import com.myportfolio.experiences.model.WorkExperience

data class RouteArgument<T>(val name: String, val type: NavType<T>)

sealed class Routes(val name: String) {
    object MyProfile : Routes("my_profile")
    object MyExperiences : Routes("my_experiences")
    object MyProjects : Routes("my_projects")

//    data class ExperienceDetail(
//        val argumentName: String,
//        val argumentType: NavType<*>
//    ) : Routes("experience_detail/{experience_id}")

//    data class Exp() : Routes()

    class E(val nameWithArgs: String? = null) : Routes("experience_detail/{experience_id}") {
        companion object {
            fun create(workExperience: WorkExperience): E {
                return E("experience_detail/${workExperience.id}")
            }
        }
    }

    sealed class RoutesWithArguments(
        name: String,
        val arguments: List<RouteArgument<*>>
    ) : Routes(name) {
        object ExperienceDetail : RoutesWithArguments(
            "experience_detail/{experience_id}",
            listOf(
                RouteArgument(
                    "experience_id",
                    NavType.LongType
                )
            )
        ) {
            fun create(experience: WorkExperience) = "experience_detail/${experience.id}"
        }
    }
}