package com.unison.appproductos.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // Import necesario para ajustar tamaños de fuente
import androidx.navigation.NavHostController
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ArrowBack
import com.unison.appproductos.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartaPresentacion(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Carta de Presentación",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp) // Aumentar tamaño de fuente
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Flecha de retroceso",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(32.dp) // Aumentar tamaño del ícono
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp), // Ajustar márgenes
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Imagen circular de perfil
            Image(
                painter = painterResource(id = R.drawable.foto),
                contentDescription = "Imagen de Perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(220.dp) // Aumentar tamaño de la imagen
                    .clip(CircleShape)
                    .border(6.dp, MaterialTheme.colorScheme.secondary, CircleShape) // Aumentar grosor del borde
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Nombre y título centrados
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Nombre
                Text(
                    text = "Jose Ignacio Zavala Rocha",
                    style = MaterialTheme.typography.headlineMedium.copy(fontSize = 28.sp), // Aumentar tamaño de fuente
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold // Añadir negrita al nombre
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Título
                Text(
                    text = "Software Engineer",
                    style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp),
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Cuadro con información centrada
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                    .padding(24.dp) // Aumentar padding interno
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Número de teléfono
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp) // Añadir padding vertical
                    ) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "Icono Teléfono",
                            modifier = Modifier.size(28.dp), // Aumentar tamaño del ícono
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "6442107423",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    // Usuario o red social
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Icono Usuario",
                            modifier = Modifier.size(28.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "JIZR-DEV",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    // Correo electrónico
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Icono Correo",
                            modifier = Modifier.size(28.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "A221211613@unison.mx",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
