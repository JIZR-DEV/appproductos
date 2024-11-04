package com.unison.appproductos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.unison.appproductos.navigation.NavManager
import com.unison.appproductos.room.ProductDatabase
import com.unison.appproductos.ui.theme.AppProductosTheme
import com.unison.appproductos.viewmodels.ProductosViewModel
import com.unison.appproductos.viewmodels.ProductosViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicialización de la base de datos Room
        val db = Room.databaseBuilder(
            applicationContext,
            ProductDatabase::class.java, "productos-database"
        ).build()

        val productDao = db.productDao()

        setContent {
            // Aplicar el tema
            AppProductosTheme {
                // Inicializamos el ViewModel con el DAO de la base de datos
                val viewModel: ProductosViewModel = viewModel(
                    factory = ProductosViewModelFactory(productDao)
                )
                val navController = rememberNavController()

                // Superficie principal que cubre toda la pantalla
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Gestor de navegación
                    NavManager(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}
