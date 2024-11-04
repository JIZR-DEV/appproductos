package com.unison.appproductos.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.unison.appproductos.viewmodels.ProductosViewModel
import com.unison.appproductos.views.*

@Composable
fun NavManager(
    navController: NavHostController,
    viewModel: ProductosViewModel
) {
    // Recoge el estado de los productos desde el ViewModel usando collectAsState()
    val productosState = viewModel.productos.collectAsState()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") {
            Inicio(
                onProductosClick = { navController.navigate("listado") },
                onPresentacionClick = { navController.navigate("presentacion") },
                navController = navController
            )
        }
        composable("listado") {
            ListadoProductos(
                productos = productosState.value,
                onAgregarProductoClick = { navController.navigate("formulario") },
                onEditClick = { producto ->
                    // Navegamos a la pantalla de edición pasando el producto seleccionado
                    navController.navigate("editar/${producto.id}")
                },
                onDeleteClick = { viewModel.eliminarProducto(it) },
                navController = navController
            )
        }
        composable("formulario") {
            FormularioProductos(
                viewModel = viewModel,
                onAgregarClick = {
                    navController.navigate("listado") // Una vez agregado, navega al listado
                },
                navController = navController
            )
        }
        composable("editar/{productoId}") { backStackEntry ->
            val productoId = backStackEntry.arguments?.getString("productoId")
            val producto = productosState.value.find { it.id == productoId }

            if (producto != null) {
                FormularioProductos(
                    viewModel = viewModel,
                    productoEdit = producto, // Pasamos el producto para edición
                    onAgregarClick = {
                        navController.navigate("listado") // Regresamos al listado después de la edición
                    },
                    navController = navController
                )
            }
        }
        composable("presentacion") {
            CartaPresentacion(navController)
        }
    }
}
