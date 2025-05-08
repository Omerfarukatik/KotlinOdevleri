package com.example.hesapmakinesi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hesapmakinesi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var sayi1 = ""
    private var sayi2 = ""
    private var ikinciMi = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val sayilar = listOf(binding.btn0,binding.btn1,binding.btn2,binding.btn3,binding.btn4,binding.btn5,binding.btn6,binding.btn7,binding.btn8,binding.btn9)

        for (button in sayilar){
            button.setOnClickListener {

                val sayi = button.text.toString()
                if (ikinciMi){
                    sayi2 += sayi
                    binding.textViewInput.text = sayi1 + "+" + sayi2
                }else{
                    sayi1 += sayi
                    binding.textViewInput.text = sayi1
                }

            }
        }

        binding.btnPlus.setOnClickListener {
            if (sayi1.isNotEmpty()){
                ikinciMi = true
                binding.textViewInput.text = "$sayi1+"
            }
        }

        binding.btnEquals.setOnClickListener {
            if (sayi1.isNotEmpty() && sayi2.isNotEmpty()){
                val sonuc = sayi1.toInt() + sayi2.toInt()
                binding.textViewInput.text = sonuc.toString()

                sayi1 = sonuc.toString()
                sayi2 = ""
                ikinciMi= false
            }
        }

        binding.btnClear.setOnClickListener {
            sayi1= ""
            sayi2 = ""
            ikinciMi = false
            binding.textViewInput.text = ""
        }

    }
}