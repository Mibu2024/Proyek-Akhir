package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model

data class ParentItem(
    val title: String,
    val childItemList: ArrayList<ChildItem>,
    var isExpandable: Boolean = false
)
