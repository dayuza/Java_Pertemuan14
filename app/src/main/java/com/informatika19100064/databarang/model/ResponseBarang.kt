package com.informatika19100064.databarang.model

import com.google.gson.annotations.SerializedName

data class ResponseBarang(

    @field:SerializedName( "pesan")
    val pesan: String? = null,

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

data class DataItem(

    @field:SerializedName("Nama_barang")
    val namaBarang: String? = null,

    @field:SerializedName("Id")
    val id: String? = null,

    @field:SerializedName("Jumlah_barang")
    val jumlahBarang: String? =null
)