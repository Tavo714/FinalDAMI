package com.pixelpulse.app_final.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.pixelpulse.app_final.ListaOpciones
import com.pixelpulse.app_final.R

class itemAdapter(val context: Context, val opciones: MutableList<ListaOpciones>):BaseAdapter() {
    override fun getCount(): Int {
        return opciones.size
    }

    override fun getItem(position: Int): Any {
        return opciones.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context);
        val vista = inflater.inflate(R.layout.lista_opciones, parent, false)
        val item = getItem(position) as ListaOpciones
        val imgOpcion = vista.findViewById<ImageView>(R.id.imgOpcion)
        val opcion = vista.findViewById<TextView>(R.id.Opcion)
        opcion.setText(item.opcion)
        imgOpcion.setImageResource(item.imgOpcion)
        return vista;
    }
}
