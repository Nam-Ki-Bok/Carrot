package com.example.carrot.Adapter

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.carrot.R
import com.example.carrot.TestData
import org.w3c.dom.Text

class ListAdapter(private val list: ArrayList<TestData>): RecyclerView.Adapter<ListAdapter.ListItemViewHolder>() {
    //inner class로 ViewHolder 정의
    inner class ListItemViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var image: ImageView = itemView!!.findViewById(R.id.imgHome)
        var title: TextView = itemView!!.findViewById(R.id.tvHomeTitle)
        var price: TextView = itemView!!.findViewById(R.id.tvHomePrice)

        fun bind(data: TestData, position: Int) {
            Log.d("ListAdapter", "===== ===== ===== bind ===== ===== =====")
            image.setImageURI(Uri.parse(data.getImage()))
            title.text = data.getTitle()
            price.text = data.getPrice()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listview_homeitem, parent, false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count()
    }
    override fun onBindViewHolder(holder: ListAdapter.ListItemViewHolder, position: Int) {
        Log.d("ListAdapter", "===== ===== ===== onBindViewHolder ===== ===== =====")
        holder.bind(list[position], position)
    }

}
