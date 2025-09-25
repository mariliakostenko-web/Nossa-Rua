package com.upx.nossarua.presentation.ui.common

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.upx.nossarua.domain.model.BottomBarItems
import com.upx.nossarua.presentation.theme.NossaRuaTheme

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    selectedItem: BottomBarItems,
    onClick: (BottomBarItems) -> Unit
) {
    NavigationBar(modifier) {
        BottomBarItems.entries.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.itemName
                    )
                },
                label = {
                    Text(
                        text = item.itemName,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                selected = selectedItem.ordinal == index,
                onClick = { onClick(item) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.2f
                    ),
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    NossaRuaTheme {
        BottomBar(
            selectedItem = BottomBarItems.MAP,
            onClick = {}
        )
    }
}
