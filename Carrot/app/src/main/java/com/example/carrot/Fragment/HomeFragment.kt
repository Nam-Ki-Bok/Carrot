package com.example.carrot.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.carrot.Adapter.ListViewAdapter
import com.example.carrot.AddItemActivity
import com.example.carrot.Item.ListViewItem
import com.example.carrot.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_home, container, false)
        view.btnAdd.setOnClickListener {
            startActivity(Intent(activity, AddItemActivity::class.java))
        }
        return view


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(activity, AddItemActivity::class.java)
        val size = intent.getIntExtra("ImageList", 0)


        val items = mutableListOf<ListViewItem>()


        //items.add(ListViewItem(ContextCompat.getDrawable(this, )!!, "", ""))

        //val adapter = ListViewAdapter(items)
        //lvHome.adapter = adapter

        //TODO item 클릭 시 이벤트 처리
        /*lvHome.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(activity, item.title, Toast.LENGTH_SHORT).show()
        }*/

    }

    private fun addItemList() {

    }

    private fun refresh() {
        Log.d("refresh", "완료")
        pullToRefresh.isRefreshing = false
    }
}
