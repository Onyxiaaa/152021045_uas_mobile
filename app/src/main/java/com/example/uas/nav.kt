package com.example.uas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class nav : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)
        var bottomnavmenu = findViewById<BottomNavigationView>(R.id.bottom_nav)
        loadFragment(FHome())
        bottomnavmenu.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bot_menu_home ->{
                    loadFragment(FHome())
                    true
                }
                R.id.bot_menu_data ->{
                    loadFragment(FData())
                    true
                }

                else -> {
                    false
                }

            }
        }
    }

    fun loadFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container,fragment)
            commit()
        }

    }
}