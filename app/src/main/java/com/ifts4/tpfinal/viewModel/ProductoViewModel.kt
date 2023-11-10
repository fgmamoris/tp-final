package com.ifts4.tpfinal.viewModel

import com.ifts4.tpfinal.models.Producto
import com.ifts4.tpfinal.repository.ProductoRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ProductoViewModel : ViewModel() {

    private val repository = ProductoRepository()

    val allProducts: LiveData<List<Producto>> = repository.allProducts

    fun insertProducto(producto: Producto) {
        repository.insert(producto)
    }

    fun updateProducto(producto: Producto) {
        repository.update(producto)
    }

    fun deleteProducto(producto: Producto) {
        repository.delete(producto)
    }
}