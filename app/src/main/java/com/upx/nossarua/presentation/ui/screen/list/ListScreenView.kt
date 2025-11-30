package com.upx.nossarua.presentation.ui.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.upx.nossarua.R
import com.upx.nossarua.domain.model.StreetMarkerDistance
import com.upx.nossarua.domain.model.StreetMarkerType
import com.upx.nossarua.presentation.theme.NossaRuaTheme
import com.upx.nossarua.presentation.ui.common.TopBar
import com.upx.nossarua.presentation.ui.screen.BaseViewModel

@Composable
fun ListScreen(
    navController: NavController,
    viewModel: BaseViewModel,
    userLocation: LatLng
) {
    viewModel.saveLocation(userLocation)
    val uiState by viewModel.uiState.collectAsState()
    NossaRuaTheme {
        Scaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            topBar = {
                TopBar(
                    screenTitle = "Lista de Incidentes",
                    onNavigateBackClicked = {
                        navController.popBackStack()
                    })
            }
        ) { contentPadding ->
            LazyColumn(Modifier.padding(contentPadding)) {
                items(uiState.markerDistance) { marker ->
                    ListItem(marker)
                }
            }
        }
    }
}

@Composable
private fun ListItem(marker: StreetMarkerDistance) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(64.dp)
                .padding(end = 24.dp),
            painter = painterResource(
                when (marker.type) {
                    StreetMarkerType.HOLE -> R.drawable.person
                    StreetMarkerType.MISSING_SIGN -> R.drawable.sign
                    StreetMarkerType.VEGETATION -> R.drawable.forest
                    StreetMarkerType.ANIMALS -> R.drawable.paw
                }
            ),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )

        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()

        ) {
            Text(
                text = marker.title + " - " + marker.type.typeName,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = marker.description,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp
            )
            Text(
                text = String.format("%.2f", marker.distance) + "Km de distância",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic
            )
            HorizontalDivider(
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}