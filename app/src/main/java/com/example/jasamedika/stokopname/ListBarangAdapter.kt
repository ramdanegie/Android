package com.sahabatpnj.crudfan

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jasamedika.stokopname.R

class ListBarangAdapter//construktor ListMahasiswaAdapter
(private val dataBarang: List<DataBarang> //inisialisasi List dengan object DataMahasiswa
) : RecyclerView.Adapter<ListBarangAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //inflate view yang akan digunakan yaitu layout list_mahasiswa_row.xml
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_barang_row, parent, false)
        return ViewHolder(v)
    } //fungsi yang dijalankan saat ViewHolder dibuat

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataBarang[position] //inisialisasi object DataMahasiwa
        holder.mNama?.setText(data.getkodebarang()) //menset value view "mNama" sesuai data dari getNamaMahasiswa();
        holder.mNim?.setText(data.getjumlah()) //menset value view "mNim" sesuai data dari getNimMahasiswa();
    }

    override fun getItemCount(): Int {
        return dataBarang.size //mengambil item sesuai urutan
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var mNama: TextView
        internal var mNim: TextView //inisialisasi variabel

        init {
            mNama = itemView.findViewById(R.id.textListMahasiswaNama) //find layout sesuai dengan yg di list_mahasiswa_row.xml
            mNim = itemView.findViewById(R.id.textListMahasiswaNim) //find layout sesuai dengan yg di list_mahasiswa_row.xml
        }
    }
}