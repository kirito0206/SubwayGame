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
        collect_button.setOnClickListener(this)
        exchange_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            water_button -> water()
            cut_button -> cut()
            fertilize_button -> fertilize()
            achievement_button -> achievement()
            question_button -> question()
            collect_button -> collect()
            exchange_button -> exchange()
        }
    }

    private fun water(){
        if (MyApplication.getInstance().waterNumber > 10){
            MyApplication.getInstance().waterNumber -= 10
            var t = 1.0
            if (MyApplication.getInstance().flower.cutFunction > 0){
                t+=0.5
                MyApplication.getInstance().flower.cutFunction--
            }
            if (MyApplication.getInstance().flower.fertilizerFunction > 0){
                t+=1
                MyApplication.getInstance().flower.fertilizerFunction--
            }
            MyApplication.getInstance().flower.waterNumber+= (t*10).toInt()
        }
        Toast.makeText(this,"浇水了！！", Toast.LENGTH_SHORT).show()
    }

    private fun cut(){
        MyApplication.getInstance().flower.cutFunction+=5
        Toast.makeText(this,"修剪了！！", Toast.LENGTH_SHORT).show()
    }

    private fun fertilize(){
        if (MyApplication.getInstance().fertilizerNumber >0){
            MyApplication.getInstance().fertilizerNumber--
            MyApplication.getInstance().flower.fertilizerFunction+=3
            Toast.makeText(this,"施肥了！！", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"没有可施肥的肥料，快去乘车/每日问答获取吧！！", Toast.LENGTH_SHORT).show()
        }
    }

    private fun achievement(){

    }

    private fun question(){
        var intentToQuestion = Intent()
        intentToQuestion.setClass(this,QuestionActivity::class.java)
        startActivity(intentToQuestion)
    }

    private fun collect(){
        var intentToCollect = Intent()
        intentToCollect.setClass(this,CollectActivity::class.java)
        startActivity(intentToCollect)
    }

    private fun exchange(){
        var intentToExchange = Intent()
        intentToExchange.setClass(this,ExchangeActivity::class.java)
        startActivity(intentToExchange)
    }
}
