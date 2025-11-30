package com.upx.nossarua.presentation.ui.screen.create

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState
import com.upx.nossarua.R
import com.upx.nossarua.domain.model.BottomBarItems
import com.upx.nossarua.domain.model.StreetMarkerType
import com.upx.nossarua.presentation.navigation.AppDirections
import com.upx.nossarua.presentation.theme.NossaRuaTheme
import com.upx.nossarua.presentation.ui.common.BottomBar
import com.upx.nossarua.presentation.ui.common.MapMarkerComposable
import com.upx.nossarua.presentation.ui.common.TopBar

@Composable
fun CreateScreenView(
    viewModel: CreateViewModel,
    navController: NavController,
    userLocation: LatLng
) {
    val uiState by viewModel.uiState.collectAsState()
    NossaRuaTheme {
        Scaffold(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            topBar = {
                TopBar(uiState.screenTitle)
            },
            bottomBar = {
                BottomBar(
                    selectedItem = BottomBarItems.CREATE,
                    onClick = { bottomBarItems ->
                        when (bottomBarItems) {
                            BottomBarItems.MAP -> navController.navigate(AppDirections.Map.route)

                            BottomBarItems.CREATE -> {}
                        }
                    }
                )
            }
        ) { contentPadding ->
            Column(Modifier.padding(contentPadding).wrapContentHeight()) {
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition(
                        userLocation,
                        15f,
                        0f,
                        0f
                    )
                }
                GoogleMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.5f),
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(isMyLocationEnabled = true),
                    onMapClick = { latLng ->
                        cameraPositionState.position = CameraPosition(
                            latLng,
                            15f,
                            0f,
                            0f
                        )
                        viewModel.putMarker(latLng)
                    }
                ) {
                    MapMarkerComposable(
                        markers = uiState.marker
                    )
                }

                FormFields(
                    modifier = Modifier.weight(if (uiState.marker.isEmpty()) 0.1f else 0.6f),
                    viewModel = viewModel,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FormFields(
    modifier: Modifier = Modifier,
    viewModel: CreateViewModel,
) {
    val uiState by viewModel.uiState.collectAsState()
    LazyColumn(
        modifier = modifier.wrapContentHeight().padding(all = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {


            if (uiState.marker.isEmpty()) {
                Text(
                    text = "Selecione um ponto no mapa para reportar um incidente",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            } else {
                val marker = uiState.marker.first()
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Preencha os campos abaixo:",
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(6.dp))

                CustomStyledTextField(
                    value = marker.title,
                    onValueChange = { viewModel.updateTitle(it) },
                    label = "Titulo do Incidente"
                )
                Spacer(Modifier.height(6.dp))

                CustomStyledTextField(
                    value = marker.description,
                    onValueChange = { viewModel.updateDescription(it) },
                    label = "Descrição do incidente"
                )
                Spacer(Modifier.height(6.dp))


                var expanded by remember { mutableStateOf(false) }

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = marker.type.typeName,
                        onValueChange = { },
                        label = { Text("Tipo do Incidente") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        readOnly = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(8.dp)
                            ),

                        )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        StreetMarkerType.entries.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type.typeName) },
                                onClick = {
                                    viewModel.updateType(type)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
                Spacer(Modifier.height(6.dp))


                CustomStyledTextField(
                    value = uiState.cpf,
                    onValueChange = { viewModel.updateCPF(it) },
                    label = "Seu CPF",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(Modifier.height(6.dp))


                OutlinedButton(
                    onClick = { viewModel.onCreateClicked() },
                    enabled = true,
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(0.5f),
                        contentColor = MaterialTheme.colorScheme.primary,
                    ),
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.primary
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Enviar Incidente")
                }

            }
            MessageDialog(
                isVisible = uiState.showDialog,
                onDismiss = {
                    viewModel.onCloseDialog()
                },
                message = uiState.message,
                isSuccess = uiState.isSuccessDialog
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomStyledTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Unspecified)
) {
    val borderColor =
        MaterialTheme.colorScheme.primary

    TextField(
        keyboardOptions = keyboardOptions,
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = borderColor,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, borderColor, RoundedCornerShape(8.dp)),
        maxLines = 4
    )
}

@Composable
fun MessageDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    message: String,
    isSuccess: Boolean
) {
    if (isVisible) {
        Dialog(onDismissRequest = onDismiss) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                tonalElevation = 8.dp,
                color = if (isSuccess) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(
                            if (isSuccess)
                                R.drawable.ic_success
                            else
                                R.drawable.ic_warning
                        ),
                        contentDescription = null
                    )
                    Text(
                        textAlign = TextAlign.Center,
                        text = message,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }
}


