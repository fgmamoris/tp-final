package com.ifts4.tpfinal.data

import com.ifts4.tpfinal.models.Producto
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(producto: Producto)

    @Query("SELECT * FROM productos ORDER BY id ASC")
    fun getAllProductos(): LiveData<List<Producto>>

    @Update
    fun update(producto: Producto)

    @Delete
    fun delete(producto: Producto)
}