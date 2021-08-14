package com.example.carrot.View

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.model.Image
import com.example.carrot.Network.RetrofitClient
import com.example.carrot.R
import com.example.carrot.Service.SaleService
import kotlinx.android.synthetic.main.activity_additem.*
import okhttp3.internal.Util
import retrofit2.Retrofit
import java.text.NumberFormat
import java.util.*

class AddItemActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var saleService: SaleService
    private lateinit var token: String

    private var isProposal : Boolean = false
    private var pickerImages = mutableListOf<Image>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_additem)
        init()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            pickerImages = ImagePicker.getImages(data)
            addImageThumbnail()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    private fun init() {
        token = Util.readToken(this)
        initRetrofit()
        initToolbar()
        initPrice()
        initProposal()
        initMainText()
        initAddImage()
    }

    private fun initRetrofit() {
        retrofit = RetrofitClient.getInstance()
        saleService = retrofit.create(SaleService::class.java)

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
            } else {
                if(etPrice.text.toString().isNotEmpty()) {
                    val intPrice = etPrice.text.toString().toInt()
                    etPrice.text = NumberFormat.getNumberInstance(Locale.KOREA).format(intPrice).toEditable()
                }
            }
        }
    }

    private fun initProposal() {
        layoutProposal.setOnClickListener {
            isProposal = !isProposal
            setProposal(isProposal)
        }
    }

    private fun setProposal(state: Boolean) {
        if(state) {
            //TODO 가격제안을 받을 경우
            ivProposal.isSelected = true
            tvProposal.setTextColor(getColor(R.color.black))
        } else {
            //TODO 가격제안을 받지 않을 경우
            ivProposal.isSelected = false
            tvProposal.setTextColor(getColor(R.color.divider_gray))
        }
    }

    private fun initMainText() {
        etContent.hint = "올릴 게시글 내용을 작성해주세요.(가품 및 판매금지품목은 게시가 제한될 수 있어요.)"
    }

    private fun initAddImage() {
        addPhotoLayout.setOnClickListener {
            addPhoto()
        }
    }

    private fun addPhoto() {
        ImagePicker.create(this).start()
    }

    //추가된 사진 thumbnail 추가
    private fun addImageThumbnail() {
        for(i in pickerImages.indices) {
            imageAdapter.addItem(pickerImages[i])
        }
    }
}

private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)