package com.proyekakhir.mibu.user.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.user.ui.home.model.ArtikelModel

class ListArtikelHomeAdapter (private var listArtikel : ArrayList<ArtikelModel>)
    : RecyclerView.Adapter<ListArtikelHomeAdapter.MyViewHolder>() {

    var listener: OnItemClickListenerHome? = null

    interface OnItemClickListenerHome {
        fun onItemClick(item: ArtikelModel)
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.iv_item_photo)
        val title: TextView = itemView.findViewById(R.id.tv_item_name)
        val desc: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListArtikelHomeAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_artikel_user_home, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = listArtikel.size

    override fun onBindViewHolder(holder: ListArtikelHomeAdapter.MyViewHolder, position: Int) {
        val currentItem = listArtikel[position]

        holder.title.text = currentItem.judul
        holder.desc.text = currentItem.isiArtikel

        if (!currentItem.imageUrl.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(currentItem.imageUrl)
                .into(holder.poster)
        } else {
            holder.poster.setImageResource(R.drawable.cam_placeholder_logo)
        }

        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentItem)
        }
    }

    fun setData(newData: List<ArtikelModel>) {
        listArtikel.clear()
        listArtikel.addAll(newData)
        notifyDataSetChanged()
    }
}