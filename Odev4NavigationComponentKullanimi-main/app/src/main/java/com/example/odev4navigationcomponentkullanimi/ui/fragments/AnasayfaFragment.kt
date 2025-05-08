package com.example.odev4navigationcomponentkullanimi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.odev4navigationcomponentkullanimi.R
import com.example.odev4navigationcomponentkullanimi.databinding.FragmentAnasayfaBinding


class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)

        binding.buttonGitA.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.sayfaAGecis)
        }

        binding.buttonGitX.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.sayfaXGecis)
        }

        return binding.root
    }

}