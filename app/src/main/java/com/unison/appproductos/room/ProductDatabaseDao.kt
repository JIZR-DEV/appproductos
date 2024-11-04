package com.unison.appproductos.room

import androidx.room.*
import com.unison.appproductos.models.Producto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDatabaseDao {

    @Query("SELECT * FROM Producto")
    fun getAllProducts(): Flow<List<Producto>>

    @Query("SELECT * FROM Producto WHERE id = :id")
    suspend fun getProductById(id: String): Producto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Producto)

    @Update
    suspend fun updateProduct(product: Producto)

    @Delete
    suspend fun deleteProduct(product: Producto)
}
