package com.proyekakhir.mibu.user.ui.anak

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.user.api.response.DataAnaksItem

class ListAnakAdapter(var list: List<DataAnaksItem?>) : RecyclerView.Adapter<ListAnakAdapter.ViewHolder>() {
    var listener: OnItemClickListenerHome? = null

    interface OnItemClickListenerHome {
        fun onItemClick(item: DataAnaksItem)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.tv_nama_anak)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_catatan_anak, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.nama.text = currentItem?.namaAnak

        holder.itemView.setOnClickListener {
            if (currentItem != null) {
                listener?.onItemClick(currentItem)
            }
        }
    }
}