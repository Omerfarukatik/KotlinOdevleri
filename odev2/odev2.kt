package odevler

class odev2 {
    // Dereceyi Fahrenheit'a çeviren fonksiyon
    fun dereceToFahrenheit(celsius: Double): Double {
        return celsius * 1.8 + 32
    }
    // Dikdörtgenin çevresini hesaplayan fonksiyon
    fun dikdortgenCevresi(kisaKenar: Int, uzunKenar: Int): Int {
        return 2 * (kisaKenar + uzunKenar)
    }

    // Sayının faktöriyelini hesaplayan fonksiyon
    fun faktoriyel(sayi: Int): Int {
        var sonuc = 1
        for (i in 1..sayi) {
            sonuc *= i
        }
        return sonuc
    }

    // Kelimenin içinde kaç tane 'a' harfi olduğunu bulan fonksiyon
    fun aHarfSayisi(kelime: String): Int {
        var sayac = 0
        for (harf in kelime) {
            if (harf.lowercaseChar() == 'a') {
                sayac++
            }
        }
        return sayac
    }

    // Çokgenin iç açılar toplamını hesaplayan fonksiyon
    fun icAciToplami(kenarSayisi: Int): Int {
        return (kenarSayisi - 2) * 180
    }
    // Gün sayısına göre maaş hesaplayan fonksiyon
    fun maasHesapla(gunSayisi: Int): Int {
        val saatlikUcret = 10
        val mesaiUcret = 20
        val toplamSaat = gunSayisi * 8

        return if (toplamSaat <= 160) {
            toplamSaat * saatlikUcret
        } else {
            val normalMaas = 160 * saatlikUcret
            val mesaiSaatleri = toplamSaat - 160
            normalMaas + (mesaiSaatleri * mesaiUcret)
        }
    }
    // Kota miktarına göre internet ücreti hesaplayan fonksiyon
    fun kotaUcretHesapla(kotaGB: Int): Int {
        val temelUcret = 100 // 50GB'a kadar
        return if (kotaGB <= 50) {
            temelUcret
        } else {
            val ekstraGB = kotaGB - 50
            temelUcret + (ekstraGB * 4)
        }
    }

}