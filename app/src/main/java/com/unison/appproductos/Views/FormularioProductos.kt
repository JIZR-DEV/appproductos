package com.unison.appproductos.views

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // Import necesario para ajustar tamaños de fuente
import androidx.navigation.NavHostController
import com.unison.appproductos.R
import com.unison.appproductos.models.Producto
import com.unison.appproductos.viewmodels.ProductosViewModel
import java.util.*
import java.text.DecimalFormat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioProductos(
    viewModel: ProductosViewModel,
    navController: NavHostController,
    productoEdit: Producto? = null, // Producto a editar, si es null es un nuevo producto
    onAgregarClick: () -> Unit = {} // Callback por defecto para agregar producto
) {
    var nombre by remember { mutableStateOf(productoEdit?.nombre ?: "") }
    var descripcion by remember { mutableStateOf(productoEdit?.descripcion ?: "") }
    var precio by remember { mutableStateOf(productoEdit?.precio?.toString() ?: "") }
    var fechaRegistro by remember { mutableStateOf(productoEdit?.fechaRegistro ?: "") }
    var showDialog by remember { mutableStateOf(false) } // Controla si mostrar el cuadro de diálogo de error
    var showInfoDialog by remember { mutableStateOf(false) } // Controla si mostrar el cuadro de información
    var firstInvalidField by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val keyboardController = LocalSoftwareKeyboardController.current

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                fechaRegistro = String.format(Locale("es", "ES"), "%02d/%02d/%04d", dayOfMonth, month + 1, year)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    fun formatearPrecio(precioTexto: String): String {
        val precioDouble = precioTexto.toDoubleOrNull()
        return if (precioDouble != null) {
            DecimalFormat("#.##").apply { maximumFractionDigits = 2 }.format(precioDouble)
        } else {
            precioTexto
        }
    }

    fun validarCampos(): Boolean {
        firstInvalidField = when {
            nombre.isEmpty() -> "nombre"
            descripcion.isEmpty() -> "descripcion"
            precio.isEmpty() || precio.toDoubleOrNull() == null -> "precio"
            fechaRegistro.isEmpty() -> "fechaRegistro"
            else -> null
        }
        showDialog = firstInvalidField != null
        return firstInvalidField == null
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (productoEdit == null) "Formulario de Productos" else "Editar Producto",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 24.sp) // Aumentar tamaño de fuente
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("listado") }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Flecha hacia atrás",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(32.dp) // Aumentar tamaño del ícono
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    if (validarCampos()) {
                        keyboardController?.hide()
                        showInfoDialog = true // Mostrar cuadro de diálogo informativo
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                text = {
                    Text(
                        text = if (productoEdit == null) "Guardar Producto" else "Actualizar Producto",
                        style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp) // Aumentar tamaño de fuente
                    )
                },
                icon = {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp) // Aumentar tamaño del ícono
                    )
                },
                modifier = Modifier.size(height = 56.dp, width = 200.dp) // Aumentar tamaño del botón
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp), // Ajustar márgenes
            verticalArrangement = Arrangement.Top, // Cambiar para aprovechar mejor el espacio
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_caja),
                contentDescription = "Logo de Caja",
                modifier = Modifier
                    .size(250.dp) // Aumentar tamaño de la imagen
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = {
                    Text(
                        "Nombre del Producto",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp) // Aumentar tamaño de fuente
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                isError = firstInvalidField == "nombre",
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp) // Aumentar tamaño de fuente
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = {
                    Text(
                        "Descripción",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Aumentar altura para mejor visibilidad
                    .padding(vertical = 8.dp),
                maxLines = 10,
                isError = firstInvalidField == "descripcion",
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
            )

            OutlinedTextField(
                value = formatearPrecio(precio),
                onValueChange = { input ->
                    if (input.matches(Regex("^\\d*(\\.\\d{0,2})?$"))) {
                        precio = input
                    }
                },
                label = {
                    Text(
                        "Precio",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                    )
                },
                leadingIcon = {
                    Text(
                        "$",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
                isError = firstInvalidField == "precio",
                textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
            )

            OutlinedTextField(
                value = fechaRegistro,
                onValueChange = { fechaRegistro = it },
                label = {
                    Text(
                        "Fecha de Registro",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { datePickerDialog.show() },
                readOnly = true,
                isError = firstInvalidField == "fechaRegistro",
                textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(
                            "Error de validación",
                            style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp)
                        )
                    },
                    text = {
                        Text(
                            "Te faltan campos por completar.",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
                        )
                    },
                    confirmButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("OK", color = MaterialTheme.colorScheme.onPrimary, fontSize = 18.sp)
                        }
                    }
                )
            }

            if (showInfoDialog) {
                AlertDialog(
                    onDismissRequest = { showInfoDialog = false },
                    title = {
                        Text(
                            "Operación realizada",
                            style = MaterialTheme.typography.titleLarge.copy(fontSize = 22.sp)
                        )
                    },
                    text = {
                        Text(
                            if (productoEdit == null) "Producto agregado correctamente"
                            else "Producto actualizado correctamente",
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp)
                        )
                    },
                    confirmButton = {
                        Button(onClick = {
                            showInfoDialog = false
                            if (productoEdit == null) {
                                viewModel.agregarProducto(nombre, descripcion, precio.toDouble(), fechaRegistro)
                                onAgregarClick()
                            } else {
                                val productoActualizado = productoEdit.copy(
                                    nombre = nombre,
                                    descripcion = descripcion,
                                    precio = precio.toDouble(),
                                    fechaRegistro = fechaRegistro
                                )
                                viewModel.actualizarProducto(productoActualizado)
                            }
                            navController.navigate("listado")
                        }) {
                            Text("OK", color = MaterialTheme.colorScheme.onPrimary, fontSize = 18.sp)
                        }
                    }
                )
            }
        }
    }
}
