package com.sahabatpnj.crudfan;

public class DataBarang {
    //inisialisasi variabel

    var kodebarang: String = ""
    var jumlah : String =""//jumlah Mahasiswa didatabase merupakan string

    //construktor databarang
    fun DataBarang(kodebarang: String, jumlah: String) {
        this.kodebarang = kodebarang
        this.jumlah = jumlah
    }


    //getter dan setter
    fun getkodebarang(): String{
        return kodebarang
    }

    fun setkodebarang(kodebarang: String) {
        this.kodebarang = kodebarang
    }
    fun getjumlah(): String {
        return jumlah
    }
    fun setjumlah(jumlah: String) {
        this.jumlah = jumlah
    }



}