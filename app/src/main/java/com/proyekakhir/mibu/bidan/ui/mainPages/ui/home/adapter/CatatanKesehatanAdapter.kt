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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.ArtikelData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.EditArtikelFragmant
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.edit.EditKesehatanFragment
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
        val edit: TextView = itemView.findViewById(R.id.tv_edit)
        val delete: ImageView = itemView.findViewById(R.id.iv_delete_kesehatan)
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

        holder.edit.setOnClickListener {
            val dialog = EditKesehatanFragment()

            // Create a bundle to pass the selected item's values
            val args = Bundle()
            args.putSerializable("selectedItem", currentItem)
            dialog.arguments = args

            // Set the listener to receive the edited data
            dialog.listener = object : EditKesehatanFragment.EditKesehatanDialogListener {
                override fun onDialogPositiveClick(artikel: AddKesehatanKehamilanData) {
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
                val uid = currentItem.uid
                if (itemKey != null) {
                    if (uid != null) {
                        deleteItemFromFirebase(itemKey, uid)
                    }
                }

                // Handle item deletion in RecyclerView
                if (position < listKesehatan.size) {
                    listKesehatan.removeAt(position)
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
        val databaseReference = FirebaseDatabase.getInstance().getReference("CatatanKesehatanKehamilan")

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