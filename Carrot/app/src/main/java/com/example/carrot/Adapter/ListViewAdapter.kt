package com.example.carrot.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.carrot.Item.ListViewItem
import com.example.carrot.R
import kotlinx.android.synthetic.main.listview_homeitem.view.*

class ListViewAdapter (private val items: MutableList<ListViewItem>): BaseAdapter() {
    override fun getCount(): Int = items.size
    override fun getItem(position: Int): ListViewItem = items[position]
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        var convertView = view

        if(convertView == null)
            convertView = LayoutInflater.from(parent?.context).inflate(R.layout.listview_homeitem, parent,false)

        val item: ListViewItem = items[position]
        convertView!!.imgHome.setImageDrawable(item.icon)
        convertView.tvHomeTitle.text = item.title
        convertView.tvHomePrice.text = item.price

        return convertView
    }

}