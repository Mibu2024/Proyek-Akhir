package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.nifas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData

class AdapterListNifas(private var list: ArrayList<AddNifasData>) :
    RecyclerView.Adapter<AdapterListNifas.ViewHolder>() {

    var listener: OnItemClickListenerHome? = null

    interface OnItemClickListenerHome {
        fun onItemClick(item: AddNifasData)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val masalah: TextView = itemView.findViewById(R.id.tv_masalah)
        val tanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_catatan_nifas, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.masalah.text = currentItem.masalah
        holder.tanggal.text = currentItem.tanggalPeriksa

        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentItem)
        }
    }

    fun setData(newData: List<AddNifasData>) {
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()
    }
}