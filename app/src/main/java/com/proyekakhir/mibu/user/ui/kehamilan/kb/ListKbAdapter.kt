package com.proyekakhir.mibu.user.ui.kehamilan.kb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.user.api.response.DataLayananKbsItem

class ListKbAdapter(var listKb: List<DataLayananKbsItem?>) :
    RecyclerView.Adapter<ListKbAdapter.ViewHolder>() {

    var listener: OnItemClickListenerHome? = null

    interface OnItemClickListenerHome {
        fun onItemClick(item: DataLayananKbsItem)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val keluhan: TextView = itemView.findViewById(R.id.tv_keluhan)
        val tanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_kb, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = listKb.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = listKb[position]

        holder.keluhan.text = currentItem?.keluhan ?: "No keluhan available"
        holder.tanggal.text = currentItem?.tanggalPraktik ?: "No date available"

        holder.itemView.setOnClickListener {
            if (currentItem != null) {
                listener?.onItemClick(currentItem)
            }
        }
    }
}