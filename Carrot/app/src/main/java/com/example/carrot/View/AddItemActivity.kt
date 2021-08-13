package com.example.carrot.View

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.carrot.R
import kotlinx.android.synthetic.main.activity_additem.*

class AddItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_additem)
        init()
    }

    private fun init() {
        initToolbar()
        initPrice()
        //initProposal()
        //initMainText()
        //initAddImage()
    }

    private fun initToolbar() {
        addItemToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addItemToolbarFinish -> {
                    //TODO 완료버튼을 눌렀을 때 게시물 업로드
                    Log.d("btnFinish", "완료버튼을 눌렀습니다.")
                    //postContent()
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
        addItemToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun initPrice() {
        etPrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    tvWon.setTextColor(getColor(R.color.divider_gray))
                } else {
                    tvWon.setTextColor(getColor(R.color.black))
                }
            }
        })

        etPrice.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                val commaPrice = etPrice.text.toString().replace(",", "")
                etPrice.text = commaPrice.toEditable()
            }
        }
    }
}

private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)