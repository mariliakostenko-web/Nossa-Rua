package com.upx.nossarua.presentation.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.upx.nossarua.R
import com.upx.nossarua.domain.model.BottomBarItems
import com.upx.nossarua.presentation.theme.NossaRuaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    screenTitle: String,
    modifier: Modifier = Modifier,
    onListClicked: (() -> Unit)? = null,
    onNavigateBackClicked: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = screenTitle,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            if (onListClicked != null) {
                IconButton(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = onListClicked
                ) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(R.drawable.ic_list),
                        contentDescription = null
                    )
                }
            }
        },
        navigationIcon = {
            if(onNavigateBackClicked != null){
                IconButton(
                    modifier = Modifier.size(32.dp).padding(start = 8.dp),
                    onClick = onNavigateBackClicked
                ) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = null
                    )
                }
            }

        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(),
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
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
