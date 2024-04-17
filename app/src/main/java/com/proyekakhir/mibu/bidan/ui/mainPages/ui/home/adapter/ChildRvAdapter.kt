package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.ChildItem

class ChildRvAdapter(private val childList: List<ChildItem>)
    : RecyclerView.Adapter<ChildRvAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hari: TextView = itemView.findViewById(R.id.tv_hari)
        val tanggal:TextView = itemView.findViewById(R.id.tv_tanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_item_detail_ibu, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = childList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.hari.text = childList[position].hari
        holder.tanggal.text = childList[position].tanggal
    }
}