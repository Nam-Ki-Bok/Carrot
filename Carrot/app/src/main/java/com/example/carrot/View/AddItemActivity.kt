package com.example.carrot.View

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.model.Image
import com.example.carrot.Network.RetrofitClient
import com.example.carrot.R
import com.example.carrot.Service.AuthService
import com.example.carrot.Service.SaleService
import com.example.carrot.Util
import com.example.carrot.Util.Companion.readToken
import kotlinx.android.synthetic.main.activity_additem.*
import retrofit2.Retrofit
import java.text.NumberFormat
import java.util.*

class AddItemActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var saleService: SaleService
    private lateinit var token: String
    private lateinit var imageAdapter: ImageAdapter

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
        //initRetrofit()
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
            addImage()
        }
    }

    private fun addImage() {
        ImagePicker.create(this).start()
    }

    //추가된 사진 thumbnail 추가
    private fun addImageThumbnail() {
        for(i in pickerImages.indices) {
            imageAdapter.addItem(pickerImages[i])
        }
    }

    class ImageAdapter(private val context: Context, private val dataSet: ArrayList<Image>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
        private lateinit var retrofit: Retrofit
        private lateinit var deleteService: AuthService

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_addphotoitem, parent, false)
            retrofit = RetrofitClient.getInstance()
            deleteService = retrofit.create(AuthService::class.java)

            return ViewHolder(view).listen {position, _ ->
                removeImage(position)
            }
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (dataSet[position].id == -1L) {
                Glide.with(context)
                    .load("" + dataSet[position].path)
                    .into(holder.image)
            } else {
                Glide.with(context)
                    .load(dataSet[position].uri)
                    .into(holder.image)
            }
        }

        override fun getItemCount(): Int = dataSet.size

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val image: ImageView = view.findViewById(R.id.ivImage)
        }

        private fun <T: RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
            itemView.setOnClickListener {
                event.invoke(adapterPosition, itemViewType)
            }

            return this
        }

        private fun removeImage(position: Int) {
            dataSet.removeAt(position)

            if(dataSet.size > 0) {
                (context as AddItemActivity).tvPhotoCount.text = dataSet.size.toString()
                context.tvPhotoCount.setTextColor(context.getColor(R.color.carrot))
            } else {
                (context as AddItemActivity).tvPhotoCount.text = "0"
                context.tvPhotoCount.setTextColor(context.getColor(R.color.gray))
            }
            notifyDataSetChanged()
        }

        fun getItems() = dataSet

        fun addItem(item: Image) {
            dataSet.add(item)
            (context as AddItemActivity).tvPhotoCount.text = dataSet.size.toString()
            context.tvPhotoCount.setTextColor(context.getColor(R.color.carrot))
            notifyDataSetChanged()
        }

        fun getNewPriority() : Int {
            for(index in dataSet.indices) {
                if(dataSet[index].id != -1L) {
                    return index
                }
            }

            return -1
        }

    }

}

private fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)