package com.proyekakhir.mibu.user.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.user.api.response.DataBidanItem
import com.proyekakhir.mibu.user.ui.home.model.BidanData
import de.hdodenhof.circleimageview.CircleImageView

class ListBidanAdapter(var list: List<DataBidanItem?>) :
    RecyclerView.Adapter<ListBidanAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: CircleImageView = itemView.findViewById(R.id.iv_bidan)
        val nama: TextView = itemView.findViewById(R.id.tv_nama_bidan)
        val whatsapp: ImageView = itemView.findViewById(R.id.iv_wa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_data_bidan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]

        holder.nama.text = currentItem?.name
        holder.whatsapp.setOnClickListener {
            val phoneNumber = currentItem?.noTelepon
            if (phoneNumber != null) {
                sendWhatsAppMessage(holder.itemView.context, phoneNumber)
            }
        }
    }

    private fun sendWhatsAppMessage(context: Context, phoneNumber: String) {
        val sendIntent = Intent(Intent.ACTION_VIEW)
        val url = "https://api.whatsapp.com/send?phone=$phoneNumber}"
        sendIntent.data = Uri.parse(url)

        try {
            context.startActivity(sendIntent)
        } catch (ex: Exception) {
            Toast.makeText(context, "WhatsApp not installed.", Toast.LENGTH_SHORT).show()
        }
    }
}