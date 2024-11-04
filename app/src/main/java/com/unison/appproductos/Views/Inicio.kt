package com.unison.appproductos.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // Import necesario para ajustar tamaños de fuente
import androidx.navigation.NavHostController
import com.unison.appproductos.R

@Composable
fun Inicio(
    onProductosClick: () -> Unit,
    onPresentacionClick: () -> Unit,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp), // Ajustar márgenes
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo más grande para darle protagonismo
        Image(
            painter = painterResource(id = R.drawable.logo_empresa),
            contentDescription = "Logo de la Empresa",
            modifier = Modifier.size(220.dp) // Aumentar tamaño del logo
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Nombre de la empresa con una tipografía llamativa y centrado
        Text(
            text = "QK-CK (Quality Klang - Custom Kinetics)",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold, // Texto en negrita
                fontSize = 32.sp, // Aumentar tamaño de fuente
            ),
            textAlign = TextAlign.Center, // Centrar el texto
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Botón para "Productos"
        Button(
            onClick = onProductosClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(64.dp), // Aumentar altura del botón
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Productos",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp), // Aumentar tamaño de fuente
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para "Presentación"
        Button(
            onClick = onPresentacionClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(64.dp), // Aumentar altura del botón
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Presentación",
                style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp), // Aumentar tamaño de fuente
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
