package com.unison.appproductos.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unison.appproductos.room.ProductDatabaseDao

class ProductosViewModelFactory(
    private val productDatabaseDao: ProductDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductosViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductosViewModel(productDatabaseDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
