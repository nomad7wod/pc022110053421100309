package com.example.pc022110053421100309.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pc022110053421100309.R
import com.example.pc022110053421100309.model.ResumenModel

class ResumenFinancierAdapter(private var resumenList: List<ResumenModel>):
        RecyclerView.Adapter<ResumenFinancierAdapter.ViewHolder>(){
            class ViewHolder(view: View): RecyclerView.ViewHolder(view){
                val tvDesc: TextView = view.findViewById(R.id.tvDesc)
                val tvMonto: TextView = view.findViewById(R.id.tvMonto)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_resumen, parent, false))
    }

    override fun getItemCount(): Int {
        return resumenList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemresumen = resumenList[position]

    }

}