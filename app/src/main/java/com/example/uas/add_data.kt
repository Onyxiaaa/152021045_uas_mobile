package com.example.uas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.uas.data.DataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class add_data : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        val namaEditText = findViewById<EditText>(R.id.etNama)
        val no_hpEditText = findViewById<EditText>(R.id.etNoHp)
        val emailEditText = findViewById<EditText>(R.id.etEmail)


        val submitButton = findViewById<Button>(R.id.btn_adddata)
        submitButton.setOnClickListener {
            val hp = no_hpEditText.text.toString()
            val nama = namaEditText.text.toString()
            val email = emailEditText.text.toString()

            if (nama.isNotEmpty() && hp.isNotEmpty() && email.isNotEmpty()) {
                val apiService = NetworkService.apiCreate
//                val user = DataItem("0",nama,hp,email)
                val user = DataItem(
                    nama = nama,
                    noHp = hp,
                    email = email
                )

                Log.d("API_TEST", "${user}")

                apiService.addUser(user).enqueue(object : Callback<DataItem> {
                    override fun onResponse(call: Call<DataItem>, response: Response<DataItem>) {
                        if (response.isSuccessful) {
                            Log.d("API_TEST", "SUKSES KONTOL")
                            Toast.makeText(this@add_data, "User added successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Log.d("API_TEST", "GAGAL KONTOL")
                            Toast.makeText(this@add_data, "Failed to add user", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<DataItem>, t: Throwable) {
                        Log.d("API_TEST", "Error: ${t.message}")
                        Toast.makeText(this@add_data, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this@add_data, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(this@add_data,user::class.java)
            startActivity(intent)
        }
    }
}