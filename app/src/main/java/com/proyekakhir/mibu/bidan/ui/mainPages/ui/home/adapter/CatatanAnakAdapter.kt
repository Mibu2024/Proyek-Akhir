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
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.edit.EditAnakFragment
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.edit.EditKesehatanFragment
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData

class CatatanAnakAdapter(private var listAnak: ArrayList<AddDataAnak>) : RecyclerView.Adapter<CatatanAnakAdapter.MyViewHolder>() {
    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: AddDataAnak)
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.tv_nama_anak)
        val edit: TextView = itemView.findViewById(R.id.tv_edit)
        val delete: ImageView = itemView.findViewById(R.id.iv_delete_anak)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_anak, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = listAnak.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listAnak[position]
        holder.nama.text = currentItem.namaAnak

        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentItem)
        }

        holder.edit.setOnClickListener {
            val dialog = EditAnakFragment()

            // Create a bundle to pass the selected item's values
            val args = Bundle()
            args.putSerializable("selectedItem", currentItem)
            dialog.arguments = args

            // Set the listener to receive the edited data
            dialog.listener = object : EditAnakFragment.EditAnakDialogListener {
                override fun onDialogPositiveClick(artikel: AddDataAnak) {
                    // Handle the edited asset here
                    notifyDataSetChanged()
                }
            }

            // Show the dialog
            dialog.show((it.context as AppCompatActivity).supportFragmentManager, "EditAnakFragment")
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
                if (position < listAnak.size) {
                    listAnak.removeAt(position)
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
        val databaseReference = FirebaseDatabase.getInstance().getReference("CatatanAnak")

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

    fun setData(newData: List<AddDataAnak>) {
        listAnak.clear()
        listAnak.addAll(newData)
        notifyDataSetChanged()
    }
}