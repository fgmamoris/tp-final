package com.ifts4.tpfinal.ui.update

import android.app.AlertDialog
import com.ifts4.tpfinal.models.Producto
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.ifts4.tpfinal.R
import com.ifts4.tpfinal.databinding.FragmentUpdateBinding
import com.ifts4.tpfinal.viewModel.ProductoViewModel
import androidx.navigation.fragment.findNavController

class UpdateFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentUpdateBinding
    private val productoViewModel by viewModels<ProductoViewModel>()

    private var producto: Producto? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        producto = arguments?.getSerializable("producto") as Producto

        binding.etNombre.setText(producto!!.nombre)
        binding.etDescripcion.setText(producto!!.descripcion)
        binding.etPrecio.setText(producto!!.precio.toString())

        binding.btnActualizarProducto.setOnClickListener {
            validateFields(producto!!)
        }
        val menu: MenuHost = requireActivity()
        menu.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    private fun validateFields(producto: Producto) {
        val nombre = binding.etNombre.text.toString()
        val descripcion = binding.etDescripcion.text.toString()
        val precio = binding.etPrecio.text.toString()

        if (nombre.isNotEmpty() && descripcion.isNotEmpty() && precio.isNotEmpty()) {


            val producto = producto.copy(
                nombre = nombre, descripcion = descripcion, precio = precio.toDouble()
            )
            productoViewModel.updateProducto(producto = producto)

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(
                requireContext(), "Debe completar los campos obligatorios!", Toast.LENGTH_SHORT
            ).show()
        }


    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        print(menu)
        menuInflater.inflate(R.menu.menu_delete, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        print("Entro2??")
        return when (menuItem.itemId) {
            R.id.menu_delete -> {
                deleteUser()
                true
            }

            else -> {
                false
            }
        }

    }

    private fun deleteUser() {
//        val dialog = AlertDialog.Builder(requireContext())
//
//        dialog.setTitle("¿Desea Eliminar?")
//        dialog.setMessage("Esta seguro que desea eliminar el ${producto!!.id}")
//
//        dialog.setNegativeButton(getString(R.string.no_option)) { _, _ ->
//            return@setNegativeButton
//        }
//
//        dialog.setPositiveButton("Yes") { _, _ ->
//            productoViewModel.deleteProducto(producto = producto!!)
//            Toast.makeText(requireContext(), "Prodcuto eliminado!", Toast.LENGTH_SHORT).show()
//
//            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
//        }
//
//        dialog.create().show()
        val producto = this.producto ?: return

        val dialog = AlertDialog.Builder(requireContext())

        dialog.setTitle("Eliminar producto")
        dialog.setMessage("Esta seguro que desea eliminar el ${producto.id}")

        dialog.setNegativeButton(getString(R.string.no_option)) { _, _ ->
            return@setNegativeButton
        }

        dialog.setPositiveButton("Si") { _, _ ->
            productoViewModel.deleteProducto(producto = producto)
            Toast.makeText(requireContext(), "Producto eliminado!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        dialog.create().show()
    }


}