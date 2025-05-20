package com.example.todoapp.ui.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.data.entity.ToDo
import com.example.todoapp.databinding.FragmentUpdateBinding
import com.example.todoapp.ui.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ToDoViewModel by viewModels()
    private val args: UpdateFragmentArgs by navArgs() // Safe Args kullanımı

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)

        // Mevcut verileri alanlara yaz
        binding.editTextTitle.setText(args.title)
        binding.editTextDescription.setText(args.description)
        binding.textViewDate.text = args.date

        // Tarih seçici
        binding.textViewDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val dpd = DatePickerDialog(requireContext(), { _, y, m, d ->
                val selectedDate = "$d/${m + 1}/$y"
                binding.textViewDate.text = selectedDate
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            dpd.show()
        }

        // Kaydet butonu ile güncelle
        binding.buttonSave.setOnClickListener {
            val newTitle = binding.editTextTitle.text.toString()
            val newDescription = binding.editTextDescription.text.toString()
            val newDate = binding.textViewDate.text.toString()

            if (newTitle.isNotBlank()) {
                val updatedToDo = ToDo(
                    id = args.id,
                    title = newTitle,
                    description = newDescription,
                    date = newDate,
                    isDone = false // mevcut durumu da ayrıca aktarabilirsin
                )
                viewModel.updateToDo(updatedToDo)
                Toast.makeText(requireContext(), "Güncellendi", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Başlık boş olamaz", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
