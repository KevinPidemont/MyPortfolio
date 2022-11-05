package com.myportfolio.profile.model

import androidx.annotation.DrawableRes

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