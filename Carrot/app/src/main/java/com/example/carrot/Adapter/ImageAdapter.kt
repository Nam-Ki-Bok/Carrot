package com.example.carrot.Adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.example.carrot.R

class ImageAdapter(context: Context,
    itemList: ArrayList<Image>,
    isInfinite: Boolean
): LoopingPagerAdapter<Image>(context, itemList, isInfinite) {
    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.item_image_pager, container, false)
    }

    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        val image = convertView.findViewById<ImageView>(R.id.ivImage)


    }
}