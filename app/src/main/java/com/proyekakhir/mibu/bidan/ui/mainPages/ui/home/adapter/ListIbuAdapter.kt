package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData

class ListIbuAdapter(private var listIbu: ArrayList<IbuHamilData>)
    : RecyclerView.Adapter<ListIbuAdapter.MyViewHolder>() {

    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: IbuHamilData)
    }

    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val namaIbu: TextView = itemview.findViewById(R.id.tv_nama_ibu)
        val namaSuami: TextView = itemview.findViewById(R.id.tv_nama_suami)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ibu_hamil, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = listIbu.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = listIbu[position]
        holder.namaIbu.text = currentItem.fullname
        holder.namaSuami.text = currentItem.namaSuami

        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentItem)
        }
    }

    fun setData(newData: List<IbuHamilData>) {
        listIbu.clear()
        listIbu.addAll(newData)
        notifyDataSetChanged()
    }

    fun setFilteredList(assetDataList: ArrayList<IbuHamilData>){
        this.listIbu = assetDataList
        notifyDataSetChanged()
    }

    fun clear() {
        this.listIbu.clear()
        notifyDataSetChanged()
    }

}