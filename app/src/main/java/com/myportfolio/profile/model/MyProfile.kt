package com.myportfolio.profile.model

import androidx.annotation.DrawableRes

data class MyProfile(
    val sections: List<ProfileSection>
)

data class ProfileSection(
    val category: SectionCategory,
    val data: SectionData
)

@JvmInline
value class SectionCategory(val title: String)

sealed class SectionData {
    data class Presentation(
        val firstName: String,
        val lastName: String,
        val position: String,
        @DrawableRes val image: Int,
        val description: String
    ) : SectionData()

    data class Skills(
        val value: List<Skill>
    ) : SectionData()

    data class Interests(
        val value: List<Interest>
    ) : SectionData()
}

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

@JvmInline
value class Interest(val value: String)
