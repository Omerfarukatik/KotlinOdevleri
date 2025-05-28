package com.example.foodapp.repository


import com.example.foodapp.data.remote.RetrofitClient

class FoodRepository {
    suspend fun getAllFoods() = RetrofitClient.apiService.getAllFoods()
}
