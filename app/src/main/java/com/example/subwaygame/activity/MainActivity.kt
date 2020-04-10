package com.example.subwaygame.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.subwaygame.MyApplication
import com.example.subwaygame.R
import com.example.subwaygame.data.Player
import com.example.subwaygame.data.PopWindow
import kotlinx.android.synthetic.main.activity_main.*
import org.litepal.LitePal
import org.litepal.crud.LitePalSupport


class MainActivity : AppCompatActivity(),View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun addAchievement(i:Int?){
        var flag = true
        when(i){
            1 -> {
                when (MyApplication.getInstance().waterTimes) {
                    1000 -> MyApplication.getInstance().changeAchievementList(0,"1st")
                    500 -> MyApplication.getInstance().changeAchievementList(0,"2nd")
                    1 -> MyApplication.getInstance().changeAchievementList(0,"3rd")
                    else -> flag = false
                }
            }

            2 ->{
                when (MyApplication.getInstance().cutTimes) {
                    1000 -> MyApplication.getInstance().changeAchievementList(1,"1st")
                    500 -> MyApplication.getInstance().changeAchievementList(1,"2nd")
                    1 -> MyApplication.getInstance().changeAchievementList(1,"3rd")
                    else -> flag = false
                }
            }

            3 ->{
                when (MyApplication.getInstance().waterNumber) {
                    1000 -> MyApplication.getInstance().changeAchievementList(2,"1st")
                    500 -> MyApplication.getInstance().changeAchievementList(2,"2nd")
                    200 -> MyApplication.getInstance().changeAchievementList(2,"3rd")
                    else -> flag = false
                }
            }

            4 ->{
                when (MyApplication.getInstance().flowers) {
                    30 -> MyApplication.getInstance().changeAchievementList(3,"1st")
                    10 -> MyApplication.getInstance().changeAchievementList(3,"2nd")
                    10 -> MyApplication.getInstance().changeAchievementList(3,"3rd")
                    else -> flag = false
                }
            }

            5 -> when {
                MyApplication.getInstance().waterTimes >= 1000 && MyApplication.getInstance().cutTimes >= 1000 && MyApplication.getInstance().achievementList[4] != "1st" ->
                    MyApplication.getInstance().changeAchievementList(4,"1st")
                MyApplication.getInstance().waterTimes >= 500 && MyApplication.getInstance().cutTimes >= 500 && MyApplication.getInstance().achievementList[4] != "2nd"->
                    MyApplication.getInstance().changeAchievementList(4,"2nd")
                MyApplication.getInstance().waterTimes >= 1 && MyApplication.getInstance().cutTimes >= 1 && MyApplication.getInstance().achievementList[4] != "3rd"->
                    MyApplication.getInstance().changeAchievementList(4,"3rd")
                else -> flag = false
            }
        }
        if (flag){
            PopWindow.show(this,"获得新成就")
        }
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
        flower_image.setOnClickListener(this)
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
            flower_image -> flowerAnimation()
        }
    }

    private fun flowerAnimation(){
        var animationDrawable = (flower_image.background) as AnimationDrawable
        //判断是否在运行
        if (!animationDrawable.isRunning) {
            //开启帧动画
            animationDrawable.start()
        }else{
            animationDrawable.stop()
            animationDrawable.start()
        }
    }

    private fun water(){
        val players = LitePal.findAll(Player::class.java)
        Log.d("litepal", "" + players.size)
        if (MyApplication.getInstance().waterNumber > 10){
            waterAnimation()
            MyApplication.getInstance().waterNumber -= 10
            MyApplication.getInstance().waterTimes ++
            var t = 1.0
            if (MyApplication.getFlowerInstance().cutFunction > 0){
                t+=0.5
                MyApplication.getFlowerInstance().cutFunction--
            }
            if (MyApplication.getFlowerInstance().fertilizerFunction > 0){
                t+=1
                MyApplication.getFlowerInstance().fertilizerFunction--
            }
            var yet = MyApplication.getFlowerInstance().waterNumber
            MyApplication.getFlowerInstance().waterNumber+= (t*10).toInt()
            //每过一百检查一次生长阶段
            if (yet/100 != MyApplication.getFlowerInstance().waterNumber/100)
                initFlower()
            Toast.makeText(this,"浇水了！！", Toast.LENGTH_SHORT).show()
            addAchievement(1)
            addAchievement(5)
        }else
            Toast.makeText(this,"没水啦，快去获取吧！！", Toast.LENGTH_SHORT).show()
        Log.d("water",MyApplication.getInstance().waterNumber.toString()+":"+MyApplication.getFlowerInstance().waterNumber)
    }

    private fun cut(){
        MyApplication.getFlowerInstance().cutFunction+=5
        cutAnimation()
        MyApplication.getInstance().cutTimes ++
        Toast.makeText(this,"修剪了！！", Toast.LENGTH_SHORT).show()
        addAchievement(2)
        addAchievement(5)
    }

    private fun fertilize(){
        if (MyApplication.getInstance().fertilizerNumber >0){
            fertilizeAnimation()
            MyApplication.getInstance().fertilizerNumber--
            MyApplication.getFlowerInstance().fertilizerFunction+=3
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
        var flowerArray = resources.obtainTypedArray(R.array.flower_images)
        var t = MyApplication.getFlowerInstance().waterNumber
        when{
            t<100 -> flower_image.setBackgroundResource(R.drawable.anim_five_list)
            t in 100..200 -> flower_image.setImageResource(flowerArray.getResourceId(1,0))
            t in 200..400 -> flower_image.setImageResource(flowerArray.getResourceId(2,0))
            t in 400..600 -> flower_image.setImageResource(flowerArray.getResourceId(3,0))
            t in 600..800 -> flower_image.setImageResource(flowerArray.getResourceId(4,0))
            t in 800..1000 -> flower_image.setImageResource(flowerArray.getResourceId(5,0))
        }
    }

    override fun onDestroy() {
        MyApplication.getInstance().updateAll()
        MyApplication.getFlowerInstance().updateAll()
        val players = LitePal.findAll(Player::class.java)
        Log.d("why", players[0].fertilizerNumber.toString()+"?"+MyApplication.getInstance().fertilizerNumber)
        super.onDestroy()
    }

    private fun waterAnimation(){
        //平移动画
        var animationSet = AnimationSet(true)
        var translateAnimation = TranslateAnimation(
            TranslateAnimation.RELATIVE_TO_PARENT, 0F, TranslateAnimation.RELATIVE_TO_PARENT, -0.5f,
            TranslateAnimation.RELATIVE_TO_PARENT, 0F, TranslateAnimation.RELATIVE_TO_PARENT, 0F
        ).apply {
            duration = 2000
        }
        //旋转动画
        var rotateAnimation = RotateAnimation(0F, -25F).apply {
            startOffset = 2000
            duration = 1000
        }
        animationSet.addAnimation(translateAnimation)
        animationSet.addAnimation(rotateAnimation)
        water_animation.bringToFront()
        water_animation.startAnimation(animationSet)
    }

    private fun cutAnimation(){
        //平移动画
        var animationSet = AnimationSet(true)
        var translateAnimation = TranslateAnimation(
            TranslateAnimation.RELATIVE_TO_PARENT, 0F, TranslateAnimation.RELATIVE_TO_PARENT, -0.5f,
            TranslateAnimation.RELATIVE_TO_PARENT, 0F, TranslateAnimation.RELATIVE_TO_PARENT, 0F
        ).apply { duration = 2000 }
        var translateAnimation1 = TranslateAnimation(
            TranslateAnimation.RELATIVE_TO_PARENT, 0F, TranslateAnimation.RELATIVE_TO_PARENT, 0f,
            TranslateAnimation.RELATIVE_TO_PARENT, 0F, TranslateAnimation.RELATIVE_TO_PARENT, -0.1F
        ).apply { duration = 500
            startOffset = 2500}
        var translateAnimation2 = TranslateAnimation(
            TranslateAnimation.RELATIVE_TO_PARENT, 0F, TranslateAnimation.RELATIVE_TO_PARENT, 0f,
            TranslateAnimation.RELATIVE_TO_PARENT, 0F, TranslateAnimation.RELATIVE_TO_PARENT, 0.08F
        ).apply { duration = 500
            startOffset = 3000}

        animationSet.addAnimation(translateAnimation)
        animationSet.addAnimation(translateAnimation1)
        animationSet.addAnimation(translateAnimation2)
        cut_animation.bringToFront()
        cut_animation.startAnimation(animationSet)
    }

    private fun fertilizeAnimation(){
        //平移动画
        var animationSet = AnimationSet(true)
        var translateAnimation = TranslateAnimation(
            TranslateAnimation.RELATIVE_TO_PARENT, 0F, TranslateAnimation.RELATIVE_TO_PARENT, 0.5f,
            TranslateAnimation.RELATIVE_TO_PARENT, 0F, TranslateAnimation.RELATIVE_TO_PARENT, 0F
        ).apply { duration = 2000 }
        //旋转动画
        var rotateAnimation = RotateAnimation(0F,25F).apply {
            startOffset = 2000
            duration = 1000
        }

        animationSet.addAnimation(translateAnimation)
        animationSet.addAnimation(rotateAnimation)
        fertilize_animation.bringToFront()
        fertilize_animation.startAnimation(animationSet)
    }
}
