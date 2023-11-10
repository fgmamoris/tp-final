package com.ifts4.tpfinal.ui.add

import com.ifts4.tpfinal.models.Producto
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ifts4.tpfinal.R
import com.ifts4.tpfinal.databinding.FragmentAddBinding
import com.ifts4.tpfinal.viewModel.ProductoViewModel


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val productoViewModel by viewModels<ProductoViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        binding.btnAddProducto.setOnClickListener {

            val nombre = binding.etNombre.text.toString()
            val descripcion = binding.etDescripcion.text.toString()
            val precio = binding.etPrecio.text.toString()

            if (nombre.isNotEmpty() && descripcion.isNotEmpty() && precio.isNotEmpty()) {
                val producto = Producto(0, nombre, descripcion, precio.toDouble())
                productoViewModel.insertProducto(producto = producto)
                Log.d("AddFragment", "com.ifts4.tpfinal.models.Producto creado! $producto")
                findNavController().navigate(R.id.action_addFragment_to_listFragment)

            } else {
                Toast.makeText(
                    requireContext(),
                    "Debe completar los campos obligatorios!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }


        return binding.root
    }

    fun Double?.isNullOrZero() = this == null || this == 0.0

}