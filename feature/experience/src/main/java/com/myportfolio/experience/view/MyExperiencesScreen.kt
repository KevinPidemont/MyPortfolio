package com.myportfolio.experience.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myportfolio.experience.ExperienceUiState
import com.myportfolio.experience.ExperienceViewModel
import com.myportfolio.experience.model.WorkExperience
import com.myportfolio.experience.model.WorkExperienceList
import com.myportfolio.theme.*

@Composable
fun MyExperiencesScreen(
    onExperienceSelected: (Long) -> Unit,
    viewModel: ExperienceViewModel = viewModel()
) {
    val state = viewModel.uiState

    // TODO show a loader and handle error
    when (val result = state.value) {
        is ExperienceUiState.Success -> WorkExperienceList(experienceList = result.experienceList) {
            onExperienceSelected(it.id)
        }
    }
}

@Composable
private fun WorkExperienceList(
    experienceList: WorkExperienceList,
    onSelected: (WorkExperience) -> Unit
) {
    LazyColumn(contentPadding = PaddingValues(SpacingLarge)) {
        itemsIndexed(items = experienceList.experiences) { index, item ->
            WorkExperienceListItem(
                paddingTop = if (index == 0) 0.dp else SpacingMedium,
                workExperience = item,
                onSelected = onSelected
            )
        }
    }
}

@Composable
private fun WorkExperienceListItem(
    paddingTop: Dp,
    workExperience: WorkExperience,
    onSelected: (WorkExperience) -> Unit
) {
    Column {
        Spacer(modifier = Modifier.padding(top = paddingTop))
        WorkExperienceCard(
            workExperience = workExperience,
            onSelected = onSelected
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun WorkExperienceCard(
    workExperience: WorkExperience,
    onSelected: (WorkExperience) -> Unit
) {
    Card(
        onClick = { onSelected(workExperience) },
        shape = RoundedCornerShape(CardCornerRadius)
    ) {
        Column(
            modifier = Modifier
                .padding(CardInsets)
                .fillMaxWidth()
        ) {
            Text(text = workExperience.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "${workExperience.companyName} (${workExperience.fromDate} - ${workExperience.toDate})",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = workExperience.description, fontSize = 12.sp)
        }
    }
}