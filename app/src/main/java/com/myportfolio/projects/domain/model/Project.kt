package com.myportfolio.projects.domain.model

import androidx.annotation.DrawableRes

data class Project(
    val title: String,
    @DrawableRes val image: Int,
    val tags: List<Tag>
)
