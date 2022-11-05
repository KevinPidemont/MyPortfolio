package com.myportfolio.experiences

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.myportfolio.experiences.model.WorkExperience
import com.myportfolio.experiences.model.WorkExperienceList

private val WorkExperienceListDataSet = WorkExperienceList(
    experiences = listOf(
        WorkExperience(
            id = 1L,
            name = "Développeur Android & Web",
            companyName = "Imagina",
            description = """
                            Développement et maintenance de l'application mobile
                            Imagina (Android)
                            Développement et maintenance du back-office et de l'API
                            (Symfony & React)
                        """.trimIndent(),
            fromDate = "2019",
            toDate = "Aujourd'hui",
        ),
        WorkExperience(
            id = 2L,
            name = "Stage en développement mobile",
            companyName = "Soledis",
            description = """
                            Développement d'une application mobile d'e-commerce (Ionic)
                            Communication via l'API de PrestaShop pour la récupération
                            des données
                        """.trimIndent(),
            fromDate = "Avril",
            toDate = "Août 2018",
        ),
        WorkExperience(
            id = 3L,
            name = "Laune Valley Farm Hostel",
            companyName = "Killorglin, Irlande",
            description = """
                Chambre d'hôtes & ferme
                Diverses tâches de bricolages, jardinages, accueil des clients
                        """.trimIndent(),
            fromDate = "Juin",
            toDate = "Août 2017",
        ),
        WorkExperience(
            id = 4L,
            name = "Emploi saisonnier en intérim",
            companyName = "Sofrilog",
            description = """
                Manutention
                        """.trimIndent(),
            fromDate = "Été 2015",
            toDate = "Été 2016",
        )
    )
)

class ExperienceViewModel : ViewModel() {
    private val _uiState = mutableStateOf<ExperienceUiState>(
        ExperienceUiState.Success(
            WorkExperienceList(listOf())
        )
    )

    val uiState: State<ExperienceUiState>
        get() = _uiState

    init {
        getExperienceList()
    }

    fun getExperienceList() {
        _uiState.value = ExperienceUiState.Success(WorkExperienceListDataSet)
    }
}

sealed class ExperienceUiState {
    data class Success(val experienceList: WorkExperienceList) : ExperienceUiState()
}