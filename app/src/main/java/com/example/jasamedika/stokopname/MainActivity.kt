package com.example.jasamedika.stokopname

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.sahabatpnj.crudfan.ReadAllActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
class MainActivity : AppCompatActivity() {
    private var TAG = "MainActivity" //untuk melihat log
    private var etKode: EditText? = null
    private var etJml: EditText? = null//pembuatan variabel edittext
    private var btnTambah: Button? = null
    val btnLihat: Button? = null //pembuatan variabel button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: inisialisasi") //untuk log pada oncreate

        etKode = findViewById(R.id.editTextKodeBarang) //inisialisasi value etNim
        etJml = findViewById(R.id.editTextMainNamaBarang) //inisialisasi value etNama

        AndroidNetworking.initialize(getApplicationContext()) //inisialisasi library FAN
        aksiButton();//memanggil fungsi aksiButton()
    }

    private fun aksiButton() {
        buttonMainTambah.setOnClickListener(){
            var kodebrg = etKode?.text.toString()
            var jml = etJml?.text.toString()
            if (kodebrg.equals("") || jml.equals("")) {
                Toast.makeText(getApplicationContext(), "Semua data harus diisi", Toast.LENGTH_SHORT).show()
                //memunculkan toast saat form masih ada yang kosong
            } else {
                tambahdata(kodebrg, jml)
            }
        }
        buttonMainLihat.setOnClickListener() {
            //tutorial selanjutnya
            //Toast.makeText(applicationContext, "Next Tutorial ya !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ReadAllActivity::class.java)
            // start your next activity
            startActivity(intent)

        }
    }

    private fun tambahdata(kodebrg: String, jml: String) {
        //koneksi ke file create.php, jika menggunakan localhost gunakan ip sesuai dengan ip kamu
        AndroidNetworking.post("http://192.168.1.18:8000/service/transaksi/android/post-produk")
                .addBodyParameter("kodebarang", kodebrg) //id bersifat Auto_Increment tidak perlu diisi/(diisi NULL) cek create.php
                .addBodyParameter("jumlah", jml) //mengirimkan data nim_mahasiswa yang akan diisi dengan varibel nim
                .addHeaders("X-AUTH-TOKEN", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbi5sb2dpc3RpayJ9.amsHnk5s4cv1LvsIWY_fbq0NHBMomRQLUaY62GyvJm2QW0QCwgHxkYeRS918nGyhh6ovGr7Id4R_9JKQ3c66kA")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        //Handle Response
//                        Log.d(FragmentActivity.TAG, "onResponse: $response") //untuk log pada onresponse
                        Toast.makeText(applicationContext, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                        etJml?.setText("")
                        etKode?.setText("")

                        //memunculkan Toast saat data berhasil ditambahkan

                    }

                    override fun onError(error: ANError) {
                        //Handle Error
                       // Log.d(FragmentActivity.TAG, "onError: Failed$error") //untuk log pada onerror
                        Toast.makeText(applicationContext, "Data gagal ditambahkan", Toast.LENGTH_SHORT).show()
                        //memunculkan Toast saat data gagal ditambahkan
                    }
                })
    }
}
