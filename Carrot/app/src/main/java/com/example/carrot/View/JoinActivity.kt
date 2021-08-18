package com.example.carrot.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.carrot.Fragment.JoinFragment
import com.example.carrot.R


class JoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<JoinFragment>(R.id.user_container)
        }
    }
}
