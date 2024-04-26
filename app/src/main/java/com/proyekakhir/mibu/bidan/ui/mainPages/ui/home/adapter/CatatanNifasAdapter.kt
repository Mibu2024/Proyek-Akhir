package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData

class CatatanNifasAdapter(private var listNifas: ArrayList<AddNifasData>) : RecyclerView.Adapter<CatatanNifasAdapter.MyViewHolder>() {
    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: AddNifasData)
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val masalah: TextView = itemView.findViewById(R.id.tv_masalah)
        val tanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_nifas, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = listNifas.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listNifas[position]
        holder.masalah.text = currentItem.masalah
        holder.tanggal.text = currentItem.tanggalPeriksa

        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentItem)
        }
    }

    fun setData(newData: List<AddNifasData>) {
        listNifas.clear()
        listNifas.addAll(newData)
        notifyDataSetChanged()
    }
}