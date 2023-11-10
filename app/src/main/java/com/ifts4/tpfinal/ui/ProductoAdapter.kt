package com.ifts4.tpfinal.ui

import com.ifts4.tpfinal.models.Producto
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ifts4.tpfinal.R
import com.ifts4.tpfinal.databinding.ItemRecyclerviewProductoBinding

class ProductoAdapter : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    private var productosList = emptyList<Producto>()

    inner class ProductoViewHolder(private val binding: ItemRecyclerviewProductoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(producto: Producto) {
            with(binding) {
                tvId.text = producto.id.toString()
                tvNombre.text = producto.nombre
                tvDescripcion.text = producto.descripcion
                tvPrecio.text = producto.precio.toString()
                root.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putSerializable("producto", producto)
                    itemView.findNavController()
                        .navigate(R.id.action_listFragment_to_updateFragment, bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val binding = ItemRecyclerviewProductoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productosList[position]
        holder.bind(producto)
    }

    override fun getItemCount(): Int {
        return productosList.size
    }

    fun setList(productos: List<Producto>) {
        productosList = productos
        notifyDataSetChanged()
    }
}