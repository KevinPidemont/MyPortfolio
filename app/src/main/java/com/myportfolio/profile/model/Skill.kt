package com.myportfolio.profile.model

sealed class Skill(val title: String, val values: List<String>) {
    object ProgrammingLanguages : Skill(
        title = "Langages",
        values = listOf(
            "Java",
            "Kotlin",
            "PHP",
            "HTML",
            "CSS",
            "JavaScript"
        )
    )

    object Frameworks : Skill(
        title = "Frameworks",
        values = listOf(
            "Android",
            "Symfony"
        )
    )
}