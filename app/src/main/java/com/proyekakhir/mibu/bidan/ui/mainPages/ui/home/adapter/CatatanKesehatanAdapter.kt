package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.ArtikelData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.ChildItem
import org.w3c.dom.Text

class CatatanKesehatanAdapter(private var listKesehatan: ArrayList<AddKesehatanKehamilanData>) : RecyclerView.Adapter<CatatanKesehatanAdapter.MyViewHolder>() {

    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: AddKesehatanKehamilanData)
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val keluhan: TextView = itemView.findViewById(R.id.tv_keluhan)
        val tanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_kesehatan, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = listKesehatan.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listKesehatan[position]
        holder.keluhan.text = currentItem.keluhan
        holder.tanggal.text = currentItem.tanggalPeriksa

        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentItem)
        }
    }

    fun setData(newData: List<AddKesehatanKehamilanData>) {
        listKesehatan.clear()
        listKesehatan.addAll(newData)
        notifyDataSetChanged()
    }

    fun setFilteredList(assetDataList: ArrayList<AddKesehatanKehamilanData>){
        this.listKesehatan = assetDataList
        notifyDataSetChanged()
    }

    fun clear() {
        this.listKesehatan.clear()
        notifyDataSetChanged()
    }

}