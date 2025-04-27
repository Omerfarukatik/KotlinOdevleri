package odevler

fun main() {
    val odev = odev2()

    println("Fahrenheit: " + odev.dereceToFahrenheit(25.0))
    println("Dikdörtgen Çevresi: " + odev.dikdortgenCevresi(5, 10))
    println("5'in Faktöriyeli: " + odev.faktoriyel(5))
    println("'Ankara' kelimesinde 'a' harfi sayısı: " + odev.aHarfSayisi("Ankara"))
    println("6 kenarlı çokgenin iç açıları toplamı: " + odev.icAciToplami(6))
    println("20 gün çalışan kişinin maaşı: " + odev.maasHesapla(20))
    println("60 GB kota kullanan kişinin ödeyeceği ücret: " + odev.kotaUcretHesapla(60))
}
