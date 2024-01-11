package com.example.uas.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.uas.R
import com.example.uas.data.GempaItem
import com.example.uas.data.ModelListGempa

class AdapterAPI(val data: ModelListGempa?, val context: Activity, val _g: List<GempaItem?>)
    : ArrayAdapter<GempaItem>(context, R.layout.custom_list, _g){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        var _idx= rowView.findViewById<TextView>(R.id.lst_nomor)
        var _tgl = rowView.findViewById<TextView>(R.id.list_tanggal)
        var _koordinat = rowView.findViewById<TextView>(R.id.list_koordinat) // wilayah
        var _wilayah = rowView.findViewById<TextView>(R.id.list_wilayah)

        _idx.setText("#" +  (position + 1).toString())
        _tgl.setText(data?.infogempa?.gempa?.get(position)?.tanggal)
        _koordinat.setText(data?.infogempa?.gempa?.get(position)?.coordinates)
        _wilayah.setText(data?.infogempa?.gempa?.get(position)?.wilayah)
        return rowView
    }
    }

