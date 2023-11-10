package com.ifts4.tpfinal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifts4.tpfinal.R
import com.ifts4.tpfinal.databinding.FragmentListBinding
import com.ifts4.tpfinal.viewModel.ProductoViewModel

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val productoViewModel by viewModels<ProductoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        val adapter = ProductoAdapter()
        binding.recyclerViewProducto.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewProducto.adapter = adapter
        val divider = DividerItemDecoration(
            requireContext(), LinearLayoutManager(requireContext()).orientation
        )
        binding.recyclerViewProducto.addItemDecoration(divider)

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        productoViewModel.allProducts.observe(viewLifecycleOwner) { listaProductos ->
            adapter.setList(productos = listaProductos)
        }
        return binding.root
    }

}