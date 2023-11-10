package com.ifts4.tpfinal.repository

import androidx.lifecycle.LiveData
import com.ifts4.tpfinal.data.ProductoDB
import com.ifts4.tpfinal.models.Producto

class ProductoRepository {

    private val productoDao = ProductoDB.getDatabase().productoDao()

    val allProducts: LiveData<List<Producto>> = productoDao.getAllProductos()

    fun insert(producto: Producto) {
        productoDao.insert(producto)
    }

    fun update(producto: Producto) {
        productoDao.update(producto)
    }

    fun delete(producto: Producto) {
        productoDao.delete(producto)
    }
}