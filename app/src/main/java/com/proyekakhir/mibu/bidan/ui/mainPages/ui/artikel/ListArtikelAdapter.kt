package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.common.base.Strings.isNullOrEmpty
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
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
        val edit: TextView = itemView.findViewById(R.id.tv_edit)
        val delete: ImageView = itemView.findViewById(R.id.iv_delete_artikel)
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

        holder.edit.setOnClickListener {
            val dialog = EditArtikelFragmant()

            // Create a bundle to pass the selected item's values
            val args = Bundle()
            args.putSerializable("selectedItem", currentItem)
            dialog.arguments = args

            // Set the listener to receive the edited data
            dialog.listener = object : EditArtikelFragmant.EditArtikelDialogListener {
                override fun onDialogPositiveClick(artikel: ArtikelData) {
                    // Handle the edited asset here
                    notifyDataSetChanged()
                }
            }

            // Show the dialog
            dialog.show((it.context as AppCompatActivity).supportFragmentManager, "EditArtikelDialogFragmant")
        }

        holder.delete.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setTitle("Delete Item")
            alertDialogBuilder.setMessage("Are you sure you want to delete this item?")
            alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
                // Get the unique key associated with the current item
                val itemKey = currentItem.key

                // Delete the item from Firebase Realtime Database
                if (itemKey != null) {
                    deleteItemFromFirebase(itemKey)
                }

                // Handle item deletion in RecyclerView
                if (position < listArtikel.size) {
                    listArtikel.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
            alertDialogBuilder.setNegativeButton("No") { _, _ ->
                // Do nothing (user clicked "No")
            }
            alertDialogBuilder.show()
        }


        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentItem)
        }
    }

    private fun deleteItemFromFirebase(itemKey: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("artikel")

        // Remove the item from the database
        if (itemKey.isNotEmpty()) {
            databaseReference.child(itemKey).removeValue()
                .addOnSuccessListener {
                    // Handle success (if needed)
                }
                .addOnFailureListener { e ->
                    // Handle failure (if needed)
                }
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