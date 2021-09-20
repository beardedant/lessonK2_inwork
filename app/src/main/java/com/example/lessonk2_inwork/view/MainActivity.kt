package com.example.lessonk2_inwork.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.example.lessonk2_inwork.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance())
                .commit()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        Thread {
            val aaa = ""
            runOnUiThread {  }
        }.start()

        return super.onCreateView(name, context, attrs)
    }
}