package com.example.foodapp.data.remote

import com.example.foodapp.data.model.CartResponse
import com.example.foodapp.data.model.Food
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllFoods(): Response<FoodResponse>

    @FormUrlEncoded
    @POST("yemekler/sepeteYemekEkle.php")
    suspend fun addToCart(
        @Field("yemek_adi") yemek_adi: String,
        @Field("yemek_resim_adi") yemek_resim_adi: String,
        @Field("yemek_fiyat") yemek_fiyat: Int,
        @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
        @Field("kullanici_adi") kullanici_adi: String
    ): Response<Any>


    @FormUrlEncoded
    @POST("yemekler/sepettekiYemekleriGetir.php")
    suspend fun getCartItems(
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<CartResponse>

    @FormUrlEncoded
    @POST("yemekler/sepettenYemekSil.php")
    suspend fun removeFromCart(
        @Field("sepet_yemek_id") sepetYemekId: String,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<Any>





}

// JSON dönüşü için response modeli
data class FoodResponse(
    val yemekler: List<Food>,
    val success: Int
)
