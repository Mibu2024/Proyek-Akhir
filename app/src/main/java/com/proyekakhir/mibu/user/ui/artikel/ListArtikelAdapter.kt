package com.proyekakhir.mibu.user.ui.artikel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.user.api.response.DataArtikelItem

class ListArtikelAdapter (var listArtikel : List<DataArtikelItem?>)
    : RecyclerView.Adapter<ListArtikelAdapter.MyViewHolder>() {

    var listener: OnItemClickListenerHome? = null

    interface OnItemClickListenerHome {
        fun onItemClick(item: DataArtikelItem)
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.iv_item_photo)
        val title: TextView = itemView.findViewById(R.id.tv_item_name)
        val desc: TextView = itemView.findViewById(R.id.tv_item_description)
        val view: Button = itemView.findViewById(R.id.btn_view)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_artikel, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = listArtikel.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listArtikel[position]

        holder.title.text = currentItem?.judul
        holder.desc.text = currentItem?.isi

        if (!currentItem?.foto.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(currentItem?.foto)
                .into(holder.poster)
        } else {
            holder.poster.setImageResource(R.drawable.cam_placeholder_logo)
        }

        holder.itemView.setOnClickListener {
            if (currentItem != null) {
                listener?.onItemClick(currentItem)
            }
        }

        holder.view.setOnClickListener {
            if (currentItem != null) {
                listener?.onItemClick(currentItem)
            }
        }
    }
}