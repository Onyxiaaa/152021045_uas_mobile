package com.example.uas.adapter

import com.example.uas.R
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.uas.data.DataItem

class AdapterCRUD(private val context: Activity, private val userList: List<DataItem>) :
    ArrayAdapter<DataItem>(context, R.layout.activity_user, userList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var rowView = convertView
        val inflater = context.layoutInflater

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.custom_user, null, true)
            val viewHolder = ViewHolder(
                rowView.findViewById(R.id.list_nama),
                rowView.findViewById(R.id.list_nohp),
                rowView.findViewById(R.id.list_email),
            )
            rowView.tag = viewHolder
        }

        val viewHolder = rowView?.tag as ViewHolder
        val user = userList[position]

        viewHolder.nama.text = user.nama
        viewHolder.nohp.text = user.noHp
        viewHolder.email.text = user.email

        return rowView
    }

    private class ViewHolder(val nama: TextView, val nohp: TextView, val email: TextView)
}