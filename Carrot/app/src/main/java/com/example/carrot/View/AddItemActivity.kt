package com.example.carrot.View

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.carrot.Fragment.HomeFragment
import com.example.carrot.R
import kotlinx.android.synthetic.main.activity_additem.*
import kotlinx.android.synthetic.main.activity_main.*

class TestData (
    private var image: String? = null,
    private var title: String? = null,
    private var price: String? =null
) {
    fun getImage(): String? {
        return image
    }
    fun setImage(image: String) {
        this.image = image
    }
    fun getTitle(): String? {
        return title
    }
    fun setTitle(title: String) {
        this.title = title
    }
    fun getPrice(): String? {
        return price
    }
    fun setPrice(price: String) {
        this.price = price
    }
}
class AddItemActivity : AppCompatActivity() {

    private val etTitle: EditText by lazy {
        findViewById(R.id.etTitle)
    }

    private val etPrice: EditText by lazy {
        findViewById(R.id.etPrice)
    }

    private val etMainText: EditText by lazy {
        findViewById(R.id.etMainText)
    }

    private val imageUriList: MutableList<Uri> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_additem)

        setSupportActionBar(addItemToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //initBtnAddImage()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_additem_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                //TODO 뒤로가기 버튼 눌렀을 때 이벤트 처리
                finish()
                return true
            }
            R.id.addItemToolbarFinish -> {
                //TODO 완료버튼 눌렀을 때 이벤트 처리
                setDataAtFragment()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /*
    private fun initBtnAddImage() {
        btnAddImage.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    navigateImages()
                }
                shouldShowRequestPermissionRationale(android.Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                    showPermissionContextPopup()
                }
                else -> {
                    requestPermissions(
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000
                    )
                }
            }
        }
    }

     */

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1000 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //TODO 권한이 부여된 것입니다.
                    navigateImages()
                } else {
                    Toast.makeText(this, "권한을 거부하셨습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                //requestCode의 key값이 1000이 아닌 다른 값이 들어왔을 때
            }
        }
    }

    private fun showPermissionContextPopup() {
        AlertDialog.Builder(this)
            .setTitle("권한이 필요합니다.")
            .setMessage("전자액자 앱에서 사진을 불러오기 위해 권한이 필요합니다.")
            .setPositiveButton("동의하기") { _, _ ->
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
            }
            .setNegativeButton("취소하기") { _, _ -> }
            .create()
            .show()
    }

    private fun navigateImages() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 2000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            //정상적으로 이미지를 선택한 것이 아님
            return
        }
        when (requestCode) {
            2000 -> {
                val selectedImageUri: Uri? = data?.data

                if (selectedImageUri != null) {
                    //TODO 사진을 가져온 경우
                    if (imageUriList.size > 3) {
                        Toast.makeText(this, "이미 사진은 꽉 찼습니다.", Toast.LENGTH_SHORT).show()
                        return
                    }
                    imageUriList.add(selectedImageUri)
                    //imageViewList[imageUriList.size - 1].setImageURI(selectedImageUri)
                } else {
                    Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            else -> {
                Toast.makeText(this, "사진을 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //프래그먼트에 데이터 전달하기
    private fun setDataAtFragment() {

        var dataList : ArrayList<TestData>? = null

        imageUriList.forEachIndexed { index, uri ->
            dataList = arrayListOf(TestData(uri.toString(), etTitle.text.toString(), etPrice.text.toString()))
        }

        val transaction = supportFragmentManager.beginTransaction()
        //transaction.replace(
            //R.id.lvHome,
            //HomeFragment()
        //)
        transaction.commit()

        intent.putExtra("data", dataList)
    }
}
