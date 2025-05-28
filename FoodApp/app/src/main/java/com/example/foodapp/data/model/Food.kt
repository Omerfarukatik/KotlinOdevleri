
package com.example.foodapp.data.model

import java.io.Serializable

data class  Food(
    val yemek_id: String,
    val yemek_adi: String,
    val yemek_resim_adi: String,
    val yemek_fiyat: String
): Serializable

data class CartItem(
    val sepet_yemek_id: String,
    val yemek_adi: String,
    val yemek_resim_adi: String,
    val yemek_fiyat: String,
    val yemek_siparis_adet: String,
    val kullanici_adi: String
)

data class CartResponse(
    val sepet_yemekler: List<CartItem>,
    val success: Int
)