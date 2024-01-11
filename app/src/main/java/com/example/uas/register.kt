package com.example.uas

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.core.view.View
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context


class register : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        // Inflate the layout and use apply to directly access the views
//        val view = LayoutInflater.from(context).inflate(R.layout.your_layout, null)
        val emailEditText = findViewById<EditText>(R.id.register_email)
        val passwordEditText = findViewById<EditText>(R.id.register_password)
        val registerButton = findViewById<Button>(R.id.btn_register)
        val logintext = findViewById<TextView>(R.id.login_text)

        logintext.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }


// Set the click listener without using binding
        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validasi email
            if (email.isEmpty()) {
                emailEditText.error = "Email Harus Diisi"
                emailEditText.requestFocus()
                return@setOnClickListener
            }

            // Validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEditText.error = "Email Tidak Valid"
                emailEditText.requestFocus()
                return@setOnClickListener
            }

            // Validasi password
            if (password.isEmpty()) {
                passwordEditText.error = "Password Harus Diisi"
                passwordEditText.requestFocus()
                return@setOnClickListener
            }

            // Validasi panjang password
            if (password.length < 6) {
                passwordEditText.error = "Password Minimal 6 Karakter"
                passwordEditText.requestFocus()
                return@setOnClickListener
            }

            RegisterFirebase(email, password)
        }

    }
    private fun RegisterFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, login::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
