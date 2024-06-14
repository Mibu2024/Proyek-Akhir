package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.anak

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak

class AdapterListAnak(private var list: ArrayList<AddDataAnak>) :
    RecyclerView.Adapter<AdapterListAnak.ViewHolder>() {

    var listener: OnItemClickListenerHome? = null

    interface OnItemClickListenerHome {
        fun onItemClick(item: AddDataAnak)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.tv_nama_anak)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_catatan_anak, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.nama.text = currentItem.namaAnak

        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentItem)
        }
    }

    fun setData(newData: List<AddDataAnak>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }
}