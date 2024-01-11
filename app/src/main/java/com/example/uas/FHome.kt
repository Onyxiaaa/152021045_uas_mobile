package com.example.uas

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FHome.newInstance] factory method to
 * create an instance of this fragment.
 */
class FHome : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_f_home, container, false)

        val btn_profil = view.findViewById<ImageButton>(R.id.menu1)
        val btn_home = view.findViewById<ImageButton>(R.id.menu2)
        val btn_setting = view.findViewById<ImageButton>(R.id.menu3)
        val btn_logout = view.findViewById<ImageButton>(R.id.menu4)

        btn_profil.setOnClickListener{
            val pindah = Intent(requireActivity(), user::class.java)
            startActivity(pindah)
        }

        btn_home.setOnClickListener {
            val pindah = Intent(requireActivity(), home::class.java)
            startActivity(pindah)
        }

        btn_logout.setOnClickListener {
            val builderDialog = AlertDialog.Builder(requireActivity())

            builderDialog.setTitle("Logout Page")
            builderDialog.setMessage("Apakah anda yakin ingin Logout?")

            builderDialog.setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->
                val pindah = Intent(requireActivity(), login::class.java)
                startActivity(pindah)
                Toast.makeText(requireActivity(), "Anda berhasil logout", Toast.LENGTH_LONG).show()
            })
            builderDialog.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(requireActivity(), "Logout dibatalkan", Toast.LENGTH_LONG).show()
            })

            builderDialog.show()
        }

        return view
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FHome.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FHome().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}