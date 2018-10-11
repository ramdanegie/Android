package com.sahabatpnj.crudfan

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.example.jasamedika.stokopname.R

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

import com.example.jasamedika.stokopname.R.id.recyclerReadAllData
import com.example.jasamedika.stokopname.R.layout.activity_read_all

class ReadAllActivity : AppCompatActivity() {
    private var dataBarang: MutableList<DataBarang>? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_all)

        recyclerView = findViewById(R.id.recyclerReadAllData) //findId recyclerView yg ada pada activity_read_all.xml

        recyclerView!!.setHasFixedSize(true) //agar recyclerView tergambar lebih cepat
        recyclerView!!.layoutManager = LinearLayoutManager(this) //menset layout manager sebagai LinearLayout(scroll kebawah)
        dataBarang = ArrayList() //arraylist untuk menyimpan data mahasiswa
        AndroidNetworking.initialize(applicationContext) //inisialisasi FAN
        getData() // pemanggilan fungsi get data
    }

    fun getData() {
        //koneksi ke file read_all.php, jika menggunakan localhost gunakan ip sesuai dengan ip kamu
        AndroidNetworking.get("http://192.168.100.13/crud_fan/read_all.php")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray) {
                        Log.d(TAG, "onResponse: $response") //untuk log pada onresponse
                        // do anything with response
                        run {
                            //mengambil data dari JSON array pada read_all.php
                            try {
                                for (i in 0 until response.length()) {
                                    val data = response.getJSONObject(i)
                                    //adding the product to product list
                                    dataBarang!!.add(DataBarang(
                                            data.getString("kodebarang"), //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                            data.getString("jumlah") //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                    ))
                                }
                                //men inisialisasi adapter RecyclerView yang sudah kita buat sebelumnya
                                val adapter = ListBarangAdapter(this@ReadAllActivity, dataBarang)
                                recyclerView!!.adapter = adapter //menset adapter yang akan digunakan pada recyclerView
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    }

                    override fun onError(error: ANError) {
                        Log.d(TAG, "onError: $error") //untuk log pada onerror
                        // handle error
                    }
                })
    }

    companion object {
        //inisialisasi variabel
        private val TAG = "ReadAllActivity"
    }

}