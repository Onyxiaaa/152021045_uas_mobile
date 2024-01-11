package com.example.uas

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.uas.adapter.AdapterAPI
import com.example.uas.data.ModelListGempa
import com.example.uas.engine.DataConfigJSON
//import kotlinx.coroutines.DefaultExecutor.enqueue
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FData.newInstance] factory method to
 * create an instance of this fragment.
 */
class FData : Fragment() {
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
        val rootView = inflater.inflate(R.layout.fragment_f_data, container, false)
        val _listview = rootView.findViewById<ListView> (R.id.list_gempa)

        DataConfigJSON()
            .getService()
            .getDataGempa()
            .enqueue(object : Callback<ModelListGempa>{
                override fun onResponse(
                    call: Call<ModelListGempa>,
                    response: Response<ModelListGempa>
                ) {
                    Log.d("Farhan Al Farisi","data json : " + response.body())
                    _listview.adapter = AdapterAPI(response.body(), requireActivity(),
                        response.body()?.infogempa?.gempa!!
                    )
                }

                override fun onFailure(call: Call<ModelListGempa>, t: Throwable) {
                    Log.d("Farhan Al Farisi","error : " + t.message.toString())
                }

            })



        return rootView
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FData.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FData().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}