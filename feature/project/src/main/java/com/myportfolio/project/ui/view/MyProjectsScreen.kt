package com.myportfolio.project.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myportfolio.project.domain.model.Project
import com.myportfolio.project.domain.model.ProjectList
import com.myportfolio.project.ui.viewmodel.ProjectListViewModel
import com.myportfolio.theme.*

@Composable
fun MyProjectScreen(onProjectSelected: (Long) -> Unit, viewModel: ProjectListViewModel = viewModel()) {
    val state by viewModel.uiState

    SideEffect {
        viewModel.getProjectList()
    }

    UIProjectList(projectList = state.projectList, onSelected = { onProjectSelected(it.id) })
}

@Composable
private fun UIProjectList(projectList: ProjectList, onSelected: (Project) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(SpacingLarge)) {
        items(projectList.projects) {
            Spacer(modifier = Modifier.height(SpacingMedium))
            ProjectCard(project = it, onSelected = onSelected)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ProjectCard(project: Project, onSelected: (Project) -> Unit) {
    Card(
        onClick = { onSelected(project) },
        shape = RoundedCornerShape(CardCornerRadius)
    ) {
        Row(
            modifier = Modifier
                .padding(CardInsets)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f, true)) {
                Text(text = project.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(SpacingSmall))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    project.tags.forEach {
                        Text(
                            text = it.value,
                            modifier = Modifier
                                .background(
                                    BackgroundColor,
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(8.dp),
                            color = CardBackgroundColor
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(SpacingMedium))
            Image(
                painter = painterResource(id = project.image),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(BackgroundColor)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )
        }
    }
}