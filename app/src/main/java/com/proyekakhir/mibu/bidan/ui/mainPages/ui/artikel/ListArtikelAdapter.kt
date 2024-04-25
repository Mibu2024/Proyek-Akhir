package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.common.base.Strings.isNullOrEmpty
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter.ListIbuAdapter
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData

class ListArtikelAdapter (private var listArtikel : ArrayList<ArtikelData>)
    : RecyclerView.Adapter<ListArtikelAdapter.MyViewHolder>(){

    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: ArtikelData)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.iv_artikel)
        val judul: TextView = itemView.findViewById(R.id.tv_judul_artikel)
        val isi: TextView = itemView.findViewById(R.id.tv_deskripsi_artikel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artikel_bidan, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = listArtikel.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listArtikel[position]

        if (!currentItem.imageUrl.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(currentItem.imageUrl)
                .into(holder.poster)
        } else {
            holder.poster.setImageResource(R.drawable.cam_placeholder_logo)
        }

        holder.judul.text = currentItem.judul
        holder.isi.text = currentItem.isiArtikel

        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentItem)
        }
    }

    fun setData(newData: List<ArtikelData>) {
        listArtikel.clear()
        listArtikel.addAll(newData)
        notifyDataSetChanged()
    }

    fun setFilteredList(assetDataList: ArrayList<ArtikelData>){
        this.listArtikel = assetDataList
        notifyDataSetChanged()
    }

    fun clear() {
        this.listArtikel.clear()
        notifyDataSetChanged()
    }
}