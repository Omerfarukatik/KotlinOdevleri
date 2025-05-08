package com.example.odev4navigationcomponentkullanimi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import com.example.odev4navigationcomponentkullanimi.R
import com.example.odev4navigationcomponentkullanimi.databinding.FragmentSayfaABinding


class SayfaAFragment : Fragment() {
    private lateinit var binding: FragmentSayfaABinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSayfaABinding.inflate(inflater, container, false)

        binding.buttonGitB.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.sayfaBGecis)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

            }
        })


        return binding.root
    }
}