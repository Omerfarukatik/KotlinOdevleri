package com.example.odev4navigationcomponentkullanimi.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.example.odev4navigationcomponentkullanimi.R
import com.example.odev4navigationcomponentkullanimi.databinding.FragmentAnasayfaBinding
import com.example.odev4navigationcomponentkullanimi.databinding.FragmentSayfaXBinding


class SayfaXFragment : Fragment() {
    private lateinit var binding: FragmentSayfaXBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSayfaXBinding.inflate(inflater, container, false)

        binding.buttonXGitY.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.gecisSayfaY)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {

        }
        })


        return binding.root
    }
}