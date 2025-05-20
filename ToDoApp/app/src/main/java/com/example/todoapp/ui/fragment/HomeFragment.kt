package com.example.todoapp.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.ui.adapter.ToDoAdapter
import com.example.todoapp.ui.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddFragment())
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Normal tüm listeyi göster
        viewModel.toDoList.observe(viewLifecycleOwner) { list ->
            val adapter = ToDoAdapter(
                toDoList = list,
                onItemClick = { selected ->
                    val action = HomeFragmentDirections
                        .actionHomeFragmentToUpdateFragment(selected.id, selected.title, selected.description, selected.date)
                    findNavController().navigate(action)
                },
                onItemCheckToggle = { updatedToDo ->
                    viewModel.updateToDo(updatedToDo)
                },
                onItemLongClick = { toDelete ->
                    AlertDialog.Builder(requireContext())
                        .setTitle("Sil")
                        .setMessage("Bu görevi silmek istiyor musunuz?")
                        .setPositiveButton("Evet") { _, _ ->
                            viewModel.deleteToDo(toDelete)
                        }
                        .setNegativeButton("Hayır", null)
                        .show()
                }
            )
            binding.recyclerView.adapter = adapter
        }

        // Arama dinleyicisi
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { observeSearch(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { observeSearch(it) }
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun observeSearch(query: String) {
        viewModel.searchToDos(query).observe(viewLifecycleOwner) { filteredList ->
            val adapter = ToDoAdapter(
                toDoList = filteredList,
                onItemClick = { selected ->
                    val action = HomeFragmentDirections
                        .actionHomeFragmentToUpdateFragment(selected.id, selected.title, selected.description, selected.date)
                    findNavController().navigate(action)
                },
                onItemCheckToggle = { updatedToDo ->
                    viewModel.updateToDo(updatedToDo)
                },
                onItemLongClick = { toDelete ->
                    AlertDialog.Builder(requireContext())
                        .setTitle("Sil")
                        .setMessage("Bu görevi silmek istiyor musunuz?")
                        .setPositiveButton("Evet") { _, _ ->
                            viewModel.deleteToDo(toDelete)
                        }
                        .setNegativeButton("Hayır", null)
                        .show()
                }
            )
            binding.recyclerView.adapter = adapter
        }
    }

}
