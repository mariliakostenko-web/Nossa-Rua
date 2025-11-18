package com.upx.nossarua.presentation.ui.screen.error

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.upx.nossarua.presentation.theme.NossaRuaTheme

@Composable
fun ErrorScreen(
    navController: NavController?,
    errorMessage: String
) {
    NossaRuaTheme {
        Scaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
        ) { contentPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(contentPadding)
            ) {
                Icon(
                    modifier = Modifier.size(64.dp),
                    imageVector = Icons.Default.Warning,
                    contentDescription = null,
                    tint = Color.Red
                )
                Text(errorMessage)
            }
        }
    }
}