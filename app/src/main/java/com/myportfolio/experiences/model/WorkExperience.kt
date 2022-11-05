package com.myportfolio.experiences.model

data class WorkExperience(
    val id: Long,
    val name: String,
    val companyName: String,
    val description: String,
    val fromDate: String,
    val toDate: String,
)
