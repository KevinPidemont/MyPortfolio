package com.myportfolio.experience.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ExperienceDetailScreen(experienceId: Long?) {
    Text(text = experienceId.toString())
}