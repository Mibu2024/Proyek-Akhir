package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.ParentItem

class ParentRvAdapter(private val parentItemList: List<ParentItem>)
    : RecyclerView.Adapter<ParentRvAdapter.MyViewHolder>(){

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.tv_history_kategori)
        val childRv: RecyclerView = itemView.findViewById(R.id.childRv)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayoutParent)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_item_detail_ibu, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val parentItem = parentItemList[position]
        holder.title.text = parentItem.title
        holder.childRv.setHasFixedSize(true)
        holder.childRv.layoutManager = LinearLayoutManager(holder.itemView.context)

        val adapter = ChildRvAdapter(parentItem.childItemList)
        holder.childRv.adapter = adapter

        //expandable functionality
        val isExpandable = parentItem.isExpandable
        holder.childRv.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.constraintLayout.setOnClickListener {
            isAnyItemExpanded(position)

            parentItem.isExpandable = !parentItem.isExpandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = parentItemList.size

    private fun isAnyItemExpanded(position: Int){
        val temp = parentItemList.indexOfFirst {
            it.isExpandable
        }

        if (temp >= 0 && temp != position){
            parentItemList[temp].isExpandable = false
            notifyItemChanged(temp)
        }
    }
}