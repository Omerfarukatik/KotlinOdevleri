package com.example.odev4navigationcomponentkullanimi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.odev4navigationcomponentkullanimi.R
import com.example.odev4navigationcomponentkullanimi.databinding.FragmentAnasayfaBinding
import com.example.odev4navigationcomponentkullanimi.databinding.FragmentSayfaYBinding


class SayfaYFragment : Fragment() {
    private lateinit var binding: FragmentSayfaYBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSayfaYBinding.inflate(inflater, container, false)

        val geriTusu = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {

            findNavController().navigate(R.id.gecisAnasayfa)

        }

        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,geriTusu)

        return binding.root
    }


}