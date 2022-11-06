package com.myportfolio.project.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.myportfolio.project.ui.viewmodel.ProjectDetailState
import com.myportfolio.project.ui.viewmodel.ProjectDetailViewModel
import com.myportfolio.theme.*

@Composable
fun ProjectDetailScreen(projectId: Long?, viewModel: ProjectDetailViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()

    SideEffect {
        viewModel.getProjectDetail(projectId)
    }

    when (val result = state) {
        is ProjectDetailState.Loading -> LoadingScreen()
        is ProjectDetailState.Success -> ProjectDetailContent(id = result.id)
        is ProjectDetailState.Error -> ErrorScreen()
    }
}

@Composable
private fun LoadingScreen() {
    CircularProgressIndicator()
}

@Composable
private fun ErrorScreen(message: String = "Une erreur est survenue") {
    Text(text = message)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ProjectDetailContent(id: Long) {
    Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        HorizontalPager(count = 3) { page ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(CardBackgroundColor)
            ) {
                Text(
                    text = page.toString(),
                    modifier = Modifier.align(Alignment.Center),
                    color = BackgroundColor
                )
            }
        }

        Column(modifier = Modifier.padding(SpacingLarge)) {
            Text(text = "Nom du projet", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
            Spacer(modifier = Modifier.height(SpacingMedium))
            Text(text = "Description", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(SpacingSmall))
            Text(
                text = """
                Description de mon projet pouvant
                tenir sur plusieurs lignes
                comme ceci
                pour un rendu optimal.
                """.trimIndent(),
                fontSize = 14.sp
            )

            List(50) {
                "Item at $it"
            }.forEach {
                Spacer(modifier = Modifier.padding(top = SpacingSmall))
                Text(text = it)
            }
        }
    }
}