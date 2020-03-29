package com.example.subwaygame.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.subwaygame.MyApplication
import com.example.subwaygame.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView(){
        water_button.setOnClickListener(this)
        cut_button.setOnClickListener(this)
        fertilize_button.setOnClickListener(this)
        achievement_button.setOnClickListener(this)
        question_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            water_button -> water()
            cut_button -> cut()
            fertilize_button -> fertilize()
            achievement_button -> achievement()
            question_button -> question()
        }
    }

    private fun water(){
        MyApplication.getInstance().flower.waterNumber++
        Toast.makeText(this,"浇水了！！", Toast.LENGTH_SHORT).show()
    }

    private fun cut(){
        Toast.makeText(this,"修剪了！！", Toast.LENGTH_SHORT).show()
    }

    private fun fertilize(){
        Toast.makeText(this,"施肥了！！", Toast.LENGTH_SHORT).show()
    }

    private fun achievement(){

    }

    private fun question(){
        var intentToQuestion = Intent()
        intentToQuestion.setClass(this,QuestionActivity::class.java)
        startActivity(intentToQuestion)
    }
}
