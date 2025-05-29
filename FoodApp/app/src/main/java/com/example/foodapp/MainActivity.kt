package com.example.foodapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.foodapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding ile layout bağlanıyor
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // NavController ile bağlantı kuruluyor
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Bottom Navigation bağlanıyor
        binding.bottomNavigationView.setupWithNavController(navController)

        // Tıklama olayları için listener ekleniyor (isteğe bağlı)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    navController.navigate(R.id.mainFragment)
                    true
                }
                R.id.menu_cart -> {
                    navController.navigate(R.id.cartFragment)
                    true
                }
                R.id.menu_profile -> {
                    Toast.makeText(this, "Profil sayfası yakında!", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}
