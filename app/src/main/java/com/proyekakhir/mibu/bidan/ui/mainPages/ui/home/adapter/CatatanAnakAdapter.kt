package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData

class CatatanAnakAdapter(private var listAnak: ArrayList<AddDataAnak>) : RecyclerView.Adapter<CatatanAnakAdapter.MyViewHolder>() {
    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: AddDataAnak)
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.tv_nama_anak)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_anak, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = listAnak.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listAnak[position]
        holder.nama.text = currentItem.namaAnak

        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentItem)
        }
    }

    fun setData(newData: List<AddDataAnak>) {
        listAnak.clear()
        listAnak.addAll(newData)
        notifyDataSetChanged()
    }
}