package com.example.uas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.example.uas.adapter.AdapterCRUD
import com.example.uas.data.DataItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class user : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val listview = findViewById<ListView>(R.id.list_user)

        val buttonAdd = findViewById<FloatingActionButton>(R.id.fab_add)
        buttonAdd.setOnClickListener {
            val intentadd = Intent(this@user, add_data::class.java)
            startActivity(intentadd)
        }

        NetworkService.apiGet.getUserData().enqueue(object : Callback<List<DataItem>> {
            override fun onResponse(call: Call<List<DataItem>>, response: Response<List<DataItem>>) =
                if (response.isSuccessful) {
                    val userList: List<DataItem>? = response.body()
                    val adapter = userList?.let { AdapterCRUD(this@user, it) }
                    listview.adapter = adapter
                    Log.d("API_TEST", "${userList}")
                    // Tambahkan event click ke ListView
                    listview.setOnItemClickListener { parent, view, position, id ->
                        val selectedUser = adapter?.getItem(position) as DataItem // Menggunakan model DataItem
                        val intent = Intent(this@user, edit_data::class.java)
                        val user2 = DataItem(selectedUser.id, selectedUser.nama, selectedUser.noHp, selectedUser.email)

                        // Mengirim data sebagai User2 ke halaman UpdateDelete
//                        intent.putExtra("User", user2)
                        startActivity(intent)
                    }
                } else {
                    Log.d("API_TEST", "respons gagal")
                    showToast("Gagal mendapatkan data pengguna")
                }

            override fun onFailure(call: Call<List<DataItem>>, t: Throwable) {
                Log.d("API_TEST", "Terjadi kesalahan: ${t.message}")
                showToast("Terjadi kesalahan: ${t.message}")
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this@user, message, Toast.LENGTH_SHORT).show()
    }
}
