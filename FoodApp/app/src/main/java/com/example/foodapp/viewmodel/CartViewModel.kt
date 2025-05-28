package com.example.foodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.data.model.CartItem
import com.example.foodapp.data.model.Food
import com.example.foodapp.data.remote.RetrofitClient
import com.example.foodapp.util.Constants
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    fun addToCart(food: Food, adet: Int, kullaniciAdi: String = Constants.KULLANICI_ADI) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.addToCart(
                    yemek_adi = food.yemek_adi,
                    yemek_resim_adi = food.yemek_resim_adi,
                    yemek_fiyat = food.yemek_fiyat.toInt(),
                    yemek_siparis_adet = adet,
                    kullanici_adi = Constants.KULLANICI_ADI,
                )

                Log.d("AddToCart", "Kullanıcı adı: $kullaniciAdi")

                if (response.isSuccessful) {
                    Log.d("AddToCart", "Başarılı: ${response.body().toString()}")
                } else {
                    Log.e("AddToCart", "Hata kodu: ${response.code()}, mesaj: ${response.errorBody()?.string()}")
                }

            } catch (e: Exception) {
                Log.e("AddToCart", "İstisna: ${e.localizedMessage}")
            }
        }
    }


    private val _cartItems = MutableLiveData<List<CartItem>>()
    val cartItems: LiveData<List<CartItem>> = _cartItems

    fun fetchCartItems(kullaniciAdi: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getCartItems(kullaniciAdi)
                if (response.isSuccessful) {
                    val sepetListesi = response.body()?.sepet_yemekler
                    Log.d("SepetCek", "Gelen sepet: $sepetListesi")
                    _cartItems.value = sepetListesi ?: emptyList()
                } else {
                    Log.e("SepetCek", "Sunucu hatası: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("SepetCek", "İstisna: ${e.localizedMessage}")
            }
        }
    }


    fun removeFromCart(sepetYemekId: String, kullaniciAdi: String) {
        viewModelScope.launch {
            try {
                RetrofitClient.apiService.removeFromCart(sepetYemekId, kullaniciAdi)
                fetchCartItems(kullaniciAdi) // yeniden listele
            } catch (e: Exception) {
                Log.e("Sepet", "Silme hatası: ${e.localizedMessage}")
            }
        }
    }


}

