package com.example.todoapp.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.data.entity.ToDo
import com.example.todoapp.databinding.FragmentAddBinding
import com.example.todoapp.ui.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ToDoViewModel by viewModels()

    private var selectedDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        // Tarih seçimi
        binding.textViewDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dpd = DatePickerDialog(
                requireContext(),
                { _, y, m, d ->
                    selectedDate = "$d/${m + 1}/$y"
                    binding.textViewDate.text = selectedDate
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            dpd.show()
        }

        // Kaydet butonu
        binding.buttonSave.setOnClickListener {
            val title = binding.editTextTitle.text.toString().trim()
            val desc = binding.editTextDescription.text.toString().trim()

            if (title.isNotBlank() && desc.isNotBlank() && selectedDate.isNotBlank()) {
                val todo = ToDo(title = title, description = desc, date = selectedDate)
                viewModel.insertToDo(todo)
                Toast.makeText(requireContext(), "Görev eklendi", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
