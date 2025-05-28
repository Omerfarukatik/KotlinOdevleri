package com.example.foodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.data.model.Food
import com.example.foodapp.repository.FoodRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val foodRepository = FoodRepository()

    private val _foods = MutableLiveData<List<Food>>()
    val foods: LiveData<List<Food>> = _foods

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchAllFoods() {
        viewModelScope.launch {
            try {
                val response = foodRepository.getAllFoods()
                if (response.isSuccessful && response.body() != null) {
                    _foods.value = response.body()!!.yemekler
                } else {
                    _error.value = "Veri alınamadı!"
                }
            } catch (e: Exception) {
                _error.value = "Hata: ${e.localizedMessage}"
            }
        }
    }
}
