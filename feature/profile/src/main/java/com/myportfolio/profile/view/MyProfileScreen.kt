package com.myportfolio.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
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
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.myportfolio.profile.ProfileViewModel
import com.myportfolio.profile.model.MyProfile
import com.myportfolio.profile.model.ProfileSection
import com.myportfolio.profile.model.SectionCategory
import com.myportfolio.profile.model.SectionData
import com.myportfolio.theme.*

@Composable
fun MyProfileScreen(viewModel : ProfileViewModel = viewModel()) {
    val state by viewModel.uiState

    SideEffect {
        viewModel.getMyProfile()
    }

    MyProfileContent(profile = state.profile)
}

@Composable
private fun MyProfileContent(profile: MyProfile) {
    Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        profile.sections.forEachIndexed { index, item ->
            UIProfileSection(
                section = item,
                modifier = Modifier.padding(
                    top = SpacingLarge,
                    start = SpacingLarge,
                    end = SpacingLarge,
                    bottom = if (index == profile.sections.size - 1) SpacingLarge else 0.dp
                )
            )
        }
    }
}

@Composable
private fun UIProfileSection(section: ProfileSection, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        UISectionCategory(category = section.category)

        Spacer(modifier = Modifier.padding(bottom = SpacingSmall))

        when (section.data) {
            is SectionData.Presentation -> PresentationSection(presentation = section.data)
            is SectionData.Skills -> SkillsSection(skills = section.data)
            is SectionData.Interests -> InterestsSection(interests = section.data)
        }
    }
}

@Composable
private fun UISectionCategory(category: SectionCategory) {
    Text(text = category.title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
}

@Composable
private fun PresentationSection(
    presentation: SectionData.Presentation,
    modifier: Modifier = Modifier
) {
//    var isExpanded by remember {
//        mutableStateOf(false)
//    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(CardCornerRadius)
    ) {
        Column(
            modifier = Modifier.padding(CardInsets),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "${presentation.firstName} ${presentation.lastName}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = presentation.position, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.weight(1f, true))

                Image(
                    painter = painterResource(id = presentation.image),
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Text(text = presentation.description, fontSize = 12.sp)

//            AnimatedVisibility(visible = isExpanded) {
//                Column {
//                    Text(text = "Coucou c'est moi")
//                    Text(text = "Nouvelle ligne")
//                }
//            }

//            TextButton(
//                onClick = { isExpanded = !isExpanded },
//                modifier = Modifier.align(Alignment.CenterHorizontally)
//            ) {
//                val text = if (isExpanded) "Voir moins" else "Voir plus"
////                val icon = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore
//
//                Text(text = text, color = BackgroundColor)
//
//                val rotation by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f)
//
//                Icon(imageVector = Icons.Default.ExpandMore, contentDescription = null, tint = BackgroundColor, modifier = Modifier.rotate(rotation))
//            }
        }
    }
}

@Composable
private fun SkillsSection(skills: SectionData.Skills) {
    Card(shape = RoundedCornerShape(CardCornerRadius)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CardInsets)
        ) {
            skills.value.forEachIndexed { index, skill ->
                Text(
                    text = skill.title,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.padding(SpacingSmall))

                FlowRow(
                    mainAxisSize = SizeMode.Expand,
                    mainAxisAlignment = MainAxisAlignment.Center,
                    mainAxisSpacing = SpacingSmall,
                    crossAxisSpacing = SpacingSmall
                ) {
                    skill.values.forEach { item ->
                        Text(
                            text = item,
                            modifier = Modifier
                                .background(BackgroundColor, RoundedCornerShape(16.dp))
                                .padding(ChipInsets),
                            color = CardBackgroundColor
                        )
                    }
                }

                if (index < skills.value.size - 1) {
                    Spacer(modifier = Modifier.padding(SpacingMedium))
                }
            }
        }
    }
}

@Composable
private fun InterestsSection(interests: SectionData.Interests) {
    Card(shape = RoundedCornerShape(CardCornerRadius)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(CardInsets)
        ) {
            interests.value.forEach {
                Text(text = it.value)
            }
        }
    }
}