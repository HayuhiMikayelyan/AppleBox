package com.example.homeworkasynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity2 : AppCompatActivity() {
    lateinit var btn_minus: ImageView
    lateinit var btn_plus: ImageView
    lateinit var tv_appleCount: TextView
    lateinit var btn_reset: Button
    var maxCount: Int = 0
    var appleCount: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        tv_appleCount = findViewById(R.id.tv_count)
        btn_minus = findViewById(R.id.imgMinus)
        btn_plus = findViewById(R.id.imgPlus)
        btn_reset = findViewById(R.id.btn_reset)
        maxCount = intent.getIntExtra("appleMaxCount", 0)
        appleCount = intent.getIntExtra("appleCount", 0)
        tv_appleCount.text = appleCount.toString()
        Toast.makeText(this, "maxcount = $maxCount , count = $appleCount", Toast.LENGTH_LONG)
            .show()

        val task = Task()
        task.execute()
    }


    inner class Task : AsyncTask<Unit, Int, Int>(), View.OnClickListener {
        override fun doInBackground(vararg p0: Unit?): Int {
            btn_minus.setOnClickListener(this)
            btn_plus.setOnClickListener(this)
            btn_reset.setOnClickListener(this)
            return appleCount
        }

        override fun onClick(p0: View?) {

            when (p0!!.id) {
                R.id.imgMinus -> {
                    if (appleCount == 1) {
                        appleCount--
                        btn_reset.visibility = View.VISIBLE
                    }else if(appleCount>0){
                        appleCount--
                        btn_reset.visibility = View.GONE
                    }
                }
                R.id.imgPlus -> {
                    if (appleCount == maxCount-1) {
                        appleCount++
                        btn_reset.visibility = View.VISIBLE
                    } else if(appleCount<maxCount){
                        appleCount++
                        btn_reset.visibility = View.GONE
                    }
                }
                R.id.btn_reset -> {
                    appleCount = intent.getIntExtra("appleCount", 0)
                    btn_reset.visibility = View.GONE
                }
            }
            tv_appleCount.text = appleCount.toString()
        }

    }
}


