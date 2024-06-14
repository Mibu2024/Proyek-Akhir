package com.proyekakhir.mibu.user.ui.kehamilan.kesehatan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.user.api.response.DataKesehatanItem
import com.proyekakhir.mibu.user.api.response.IbuResponse
import com.proyekakhir.mibu.user.api.response.KesehatanResponse
import com.proyekakhir.mibu.user.ui.home.model.ArtikelModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.KesehatanModel

class ListKesehatanAdapter(var list: List<DataKesehatanItem?>) : RecyclerView.Adapter<ListKesehatanAdapter.ViewHolder>() {

    var listener: OnItemClickListenerHome? = null

    interface OnItemClickListenerHome {
        fun onItemClick(item: DataKesehatanItem)
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val keluhan: TextView = itemView.findViewById(R.id.tv_keluhan)
        val tanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_catatan_kesehatan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        holder.keluhan.text = currentItem?.keluhan ?: "No keluhan available"
        holder.tanggal.text = currentItem?.tanggal ?: "No date available"

        holder.itemView.setOnClickListener {
            if (currentItem != null) {
                listener?.onItemClick(currentItem)
            }
        }
    }
}