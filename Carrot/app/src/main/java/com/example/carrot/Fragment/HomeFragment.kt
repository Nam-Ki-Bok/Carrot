package com.example.carrot.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.carrot.R
import com.example.carrot.View.AddItemActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private val writeContract = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {
        initWriteButton(view)
    }

    private fun initWriteButton(view: View) {
        val btnWriteSale = view.findViewById<FloatingActionButton>(R.id.btnWriteSale)

        btnWriteSale.setOnClickListener {
            moveWriteSale()
        }
    }

    private fun moveWriteSale() {
        val intent = Intent(activity, AddItemActivity::class.java)
        startActivity(intent)
    }

}
