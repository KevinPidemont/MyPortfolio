package com.myportfolio.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.myportfolio.R
import com.myportfolio.profile.model.*

private val MyProfileData = MyProfile(
    sections = listOf(
        ProfileSection(
            category = SectionCategory("Qui suis-je"),
            data = SectionData.Presentation(
                firstName = "Kévin",
                lastName = "Pidemont",
                position = "Développeur Android & Web",
                image = R.drawable.my_profile_image,
                description = """
                    Fort d'une expérience de 4 années dans
                    le développement Android. Je souhaite
                    mettre à profit mes compétences.
                    C'est avec discipline, rigueur, curiosité et
                    passion que j'aborde mes projets.
                """.trimIndent()
            )
        ),
        ProfileSection(
            category = SectionCategory("Compétences"),
            data = SectionData.Skills(
                value = listOf(
                    Skill.ProgrammingLanguages,
                    Skill.Frameworks
                )
            )
        ),
        ProfileSection(
            category = SectionCategory("Centre d'intérêts"),
            data = SectionData.Interests(
                value = listOf(
                    Interest("Randonnée"),
                    Interest("Course à pied"),
                    Interest("Développement informatique"),
                    Interest("Développement personnel")
                )
            )
        )
    )
)

class ProfileViewModel : ViewModel() {
    private val _uiState = mutableStateOf(ProfileUiState())
    val uiState: State<ProfileUiState>
        get() = _uiState

    fun getMyProfile() {
        _uiState.value = ProfileUiState(MyProfileData)
    }
}

data class ProfileUiState(
    val profile: MyProfile = createEmptyProfile()
) {
    companion object {
        fun createEmptyProfile() = MyProfile(listOf())
    }
}