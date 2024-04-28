package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.edit.EditKesehatanFragment
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.edit.EditNifasFragment
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData

class CatatanNifasAdapter(private var listNifas: ArrayList<AddNifasData>) : RecyclerView.Adapter<CatatanNifasAdapter.MyViewHolder>() {
    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: AddNifasData)
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val masalah: TextView = itemView.findViewById(R.id.tv_masalah)
        val tanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
        val edit: TextView = itemView.findViewById(R.id.tv_edit)
        val delete: ImageView = itemView.findViewById(R.id.iv_delete_nifas)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_detail_nifas, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = listNifas.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listNifas[position]
        holder.masalah.text = currentItem.masalah
        holder.tanggal.text = currentItem.tanggalPeriksa

        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentItem)
        }

        holder.edit.setOnClickListener {
            val dialog = EditNifasFragment()

            // Create a bundle to pass the selected item's values
            val args = Bundle()
            args.putSerializable("selectedItem", currentItem)
            dialog.arguments = args

            // Set the listener to receive the edited data
            dialog.listener = object : EditNifasFragment.EditNifasDialogListener {
                override fun onDialogPositiveClick(artikel: AddNifasData) {
                    // Handle the edited asset here
                    notifyDataSetChanged()
                }
            }

            // Show the dialog
            dialog.show((it.context as AppCompatActivity).supportFragmentManager, "EditNifasFragment")
        }

        holder.delete.setOnClickListener {
            val alertDialogBuilder = AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setTitle("Delete Item")
            alertDialogBuilder.setMessage("Are you sure you want to delete this item?")
            alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
                // Get the unique key associated with the current item
                val itemKey = currentItem.key

                // Delete the item from Firebase Realtime Database
                val uid = currentItem.uid
                if (itemKey != null) {
                    if (uid != null) {
                        deleteItemFromFirebase(itemKey, uid)
                    }
                }

                // Handle item deletion in RecyclerView
                if (position < listNifas.size) {
                    listNifas.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
            alertDialogBuilder.setNegativeButton("No") { _, _ ->
                // Do nothing (user clicked "No")
            }
            alertDialogBuilder.show()
        }
    }

    private fun deleteItemFromFirebase(itemKey: String, uid: String) {
        val databaseReference = FirebaseDatabase.getInstance().getReference("CatatanNifas")

        val childPath = "$uid/$itemKey"
        // Remove the item from the database
        if (itemKey.isNotEmpty()) {
            databaseReference.child(childPath).removeValue()
                .addOnSuccessListener {
                    // Handle success (if needed)
                    Log.d("FirebaseDelete", "Item deleted successfully")
                }
                .addOnFailureListener { e ->
                    // Handle failure (if needed)
                    Log.e("FirebaseDelete", "Failed to delete item: ${e.message}")
                }
        } else {
            Log.e("FirebaseDelete", "Item key is empty")
        }
    }

    fun setData(newData: List<AddNifasData>) {
        listNifas.clear()
        listNifas.addAll(newData)
        notifyDataSetChanged()
    }
}