package com.example.subwaygame.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.subwaygame.MyApplication
import com.example.subwaygame.R
import com.example.subwaygame.data.Player
import kotlinx.android.synthetic.main.activity_main.*
import org.litepal.LitePal
import org.litepal.crud.LitePalSupport


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
        bag_button.setOnClickListener(this)
        initFlower()
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
            bag_button -> bag()
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
            var yet = MyApplication.getInstance().flower.waterNumber
            MyApplication.getInstance().flower.waterNumber+= (t*10).toInt()
            //每过一百检查一次生长阶段
            if (yet/100 != MyApplication.getInstance().flower.waterNumber/100)
                initFlower()
            Toast.makeText(this,"浇水了！！", Toast.LENGTH_SHORT).show()
        }else
            Toast.makeText(this,"没水啦，快去获取吧！！", Toast.LENGTH_SHORT).show()
        Log.d("water",MyApplication.getInstance().waterNumber.toString()+":"+MyApplication.getInstance().flower.waterNumber)
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
        var intentToAchievement = Intent()
        intentToAchievement.setClass(this,AchievementActivity::class.java)
        startActivity(intentToAchievement)
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

    private fun bag(){
        var intentToBag = Intent()
        intentToBag.setClass(this,BagActivity::class.java)
        startActivity(intentToBag)
    }

    @SuppressLint("ResourceType")
    private fun initFlower(){
        var flowerArray = resources.obtainTypedArray(R.array.one_images)
        var t = MyApplication.getInstance().flower.waterNumber
        when{
            t<100 -> flower_image.setImageResource(flowerArray.getResourceId(0,0))
            t in 100..200 -> flower_image.setImageResource(flowerArray.getResourceId(1,0))
            t in 200..400 -> flower_image.setImageResource(flowerArray.getResourceId(2,0))
            t in 400..600 -> flower_image.setImageResource(flowerArray.getResourceId(3,0))
            t in 600..800 -> flower_image.setImageResource(flowerArray.getResourceId(4,0))
            t in 800..1000 -> flower_image.setImageResource(flowerArray.getResourceId(5,0))
        }
    }

    override fun onDestroy() {
        //MyApplication.getInstance().update(0)
        super.onDestroy()
    }
}
