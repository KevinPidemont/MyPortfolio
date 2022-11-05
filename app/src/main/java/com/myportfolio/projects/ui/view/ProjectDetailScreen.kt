package com.myportfolio.projects.ui.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProjectDetailScreen(projectId: Long?) {
    Text(text = "$projectId")
}