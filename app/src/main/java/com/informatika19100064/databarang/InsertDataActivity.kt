package com.informatika19100064.databarang

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_insert_data.*

class InsertDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        toolbar.title = "INSERT DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        btn_submit.setOnClickListener {
            val etNamaBarang = et_nama_barang.text
            val etJmlBarang = et_jumlah_barang.text
            if (etJmlBarang.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Jumlah Barang Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else if (etNamaBarang.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Nama Barang Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                actionData(etNamaBarang.toString(), etJmlBarang.toString())
            }
        }

        btn_clean.setOnClickListener {
            formClear()
        }
        getData()
    }

    fun formClear() {
        et_nama_barang.text.clear()
        et_jumlah_barang.text.clear()

    }

    fun actionData(namaBarang: String, jmlBarang: String) {
        koneksi.service.insertBarang(namaBarang, jmlBarang)
            .enqueue(object : Callback<ResponseActionBarang> {
                override fun onFailure(call: Call<ResponseActionBarang>, t: Throwable) {
                    Log.d("pesan1", t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseActionBarang>,
                    response: Response<ResponseActionBarang>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@InsertDataActivity,
                            "data berhasil disimpan",
                            Toast.LENGTH_LONG
                        ).show()
                        formClear()
                        getData()
                    }
                }
            })
    }

    fun getData() {
        koneksi.service.getBarang().enqueue(object : Callback<ResponseBarang> {
            override fun onFailure(call: Call<ResponseBarang>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseBarang>,
                response: Response<ResponseBarang>
            ) {
                if (response.isSuccessful) {
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@InsertDataActivity)

                    rv_data_barang.apply {
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@InsertDataActivity)
                    }
                }
            }
        })
    }
}

