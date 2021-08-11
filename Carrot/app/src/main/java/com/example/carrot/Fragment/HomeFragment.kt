package com.example.carrot.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carrot.Adapter.ListAdapter
import com.example.carrot.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        //TODO item에 데이터 추가 시 사용하는 코드
        /*
        val items = mutableListOf<ListViewItem>()
        items.add(ListViewItem(activity?.let {
            ContextCompat.getDrawable(
                it,
                R.drawable.ic_baseline_image_search_24
            )
        }!!, "제목", "20000"))
        */

        //TODO item 클릭 시 이벤트 처리
        /*lvHome.setOnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = parent.getItemAtPosition(position) as ListViewItem
            Toast.makeText(activity, item.title, Toast.LENGTH_SHORT).show()
        }*/

    }


    private fun addItemList() {

    }

}
