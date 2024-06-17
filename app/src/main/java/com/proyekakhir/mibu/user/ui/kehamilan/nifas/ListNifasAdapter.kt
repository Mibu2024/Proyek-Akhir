package com.proyekakhir.mibu.user.ui.kehamilan.nifas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.user.api.response.DataNifasItem

class ListNifasAdapter(var list: List<DataNifasItem?>) :  RecyclerView.Adapter<ListNifasAdapter.ViewHolder>(){

    var listener: OnItemClickListenerHome? = null

    interface OnItemClickListenerHome {
        fun onItemClick(item: DataNifasItem)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val masalah: TextView = itemView.findViewById(R.id.tv_masalah)
        val tanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_catatan_nifas, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.masalah.text = currentItem?.masalah ?: "No masalah available"
        holder.tanggal.text = currentItem?.tanggal ?: "No date available"

        holder.itemView.setOnClickListener {
            if (currentItem != null) {
                listener?.onItemClick(currentItem)
            }
        }
    }
}