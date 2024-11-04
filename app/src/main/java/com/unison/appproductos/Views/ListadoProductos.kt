package com.unison.appproductos.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // Import necesario para ajustar tamaños de fuente
import androidx.navigation.NavHostController
import com.unison.appproductos.models.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListadoProductos(
    productos: List<Producto>,
    onAgregarProductoClick: () -> Unit,
    onEditClick: (Producto) -> Unit,
    onDeleteClick: (Producto) -> Unit,
    navController: NavHostController
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    var productoAEliminar by remember { mutableStateOf<Producto?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Listado de Productos",
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp // Aumentar tamaño de fuente
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("inicio") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar a Inicio",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(32.dp) // Aumentar tamaño del ícono
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAgregarProductoClick,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(72.dp) // Aumentar tamaño del FAB
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Agregar Producto",
                    modifier = Modifier.size(36.dp) // Aumentar tamaño del ícono
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp) // Añadir margen horizontal
        ) {
            if (productos.isEmpty()) {
                Text(
                    text = "No hay productos disponibles. ¡Agrega uno nuevo!",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 18.sp // Aumentar tamaño de fuente
                    ),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 16.dp) // Ajustar padding vertical
                ) {
                    items(productos) { producto ->
                        ProductoItem(
                            producto = producto,
                            onEditClick = { onEditClick(producto) },
                            onDeleteClick = {
                                productoAEliminar = producto
                                showDeleteDialog = true
                            }
                        )
                    }
                }

                if (showDeleteDialog && productoAEliminar != null) {
                    AlertDialog(
                        onDismissRequest = { showDeleteDialog = false },
                        title = {
                            Text(
                                text = "Confirmar eliminación",
                                style = MaterialTheme.typography.titleMedium.copy(fontSize = 22.sp) // Aumentar tamaño
                            )
                        },
                        text = {
                            Text(
                                "¿Estás seguro de que quieres eliminar ${productoAEliminar?.nombre}?",
                                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp) // Aumentar tamaño
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    onDeleteClick(productoAEliminar!!)
                                    showDeleteDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                            ) {
                                Text("Eliminar", color = MaterialTheme.colorScheme.onError, fontSize = 18.sp) // Aumentar tamaño
                            }
                        },
                        dismissButton = {
                            Button(onClick = { showDeleteDialog = false }) {
                                Text("Cancelar", fontSize = 18.sp) // Aumentar tamaño
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductoItem(
    producto: Producto,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Agrega elevación
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = producto.nombre,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp // Aumentar tamaño de fuente
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = producto.descripcion,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 16.sp // Aumentar tamaño de fuente
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(
                onClick = onEditClick,
                modifier = Modifier.size(48.dp) // Aumentar tamaño del botón
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Editar Producto",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp) // Aumentar tamaño del ícono
                )
            }
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.size(48.dp) // Aumentar tamaño del botón
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Eliminar Producto",
                    tint = MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(24.dp) // Aumentar tamaño del ícono
                )
            }
        }
    }
}
