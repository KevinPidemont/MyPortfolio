package com.myportfolio.base.view

import androidx.annotation.StringRes
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource

data class BottomNavBarItemData(
    @StringRes val title: Int,
    val icon: ImageVector
)

@Composable
fun BottomNavBar(items: List<BottomNavBarItemData>, selectedIndex: Int, onSelected: (Int) -> Unit) {
    BottomNavigation {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                selected = selectedIndex == index,
                onClick = { onSelected(index) },
                icon = {
                    Icon(item.icon, contentDescription = null)
                },
                label = {
                    Text(text = stringResource(id = item.title))
                }
            )
        }
    }
}