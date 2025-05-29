package com.example.foodapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentMainBinding
import com.example.foodapp.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        adapter = MainAdapter { selectedFood ->
            val bundle = Bundle().apply {
                putSerializable("food", selectedFood)
            }
            findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // Veri çekiliyor
        viewModel.fetchAllFoods()

        // Gelen yemekleri doğrudan listele
        viewModel.foods.observe(viewLifecycleOwner) { foodList ->
            adapter.submitList(foodList)
        }

        // Hata mesajı varsa göster
        viewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            errorMsg?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        // Arama kısmı (opsiyonel)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = if (!newText.isNullOrBlank()) {
                    viewModel.foods.value?.filter {
                        it.yemek_adi.contains(newText, ignoreCase = true)
                    }
                } else {
                    viewModel.foods.value
                }
                adapter.submitList(filteredList)
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
