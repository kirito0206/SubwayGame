package com.example.subwaygame.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.subwaygame.MyApplication
import com.example.subwaygame.R
import com.example.subwaygame.adapter.GiftGridAdapter
import com.example.subwaygame.data.Gift
import kotlinx.android.synthetic.main.activity_bag.*

class BagActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bag)
        initView()
        initGridView()
    }

    private fun initView(){
        Glide.with(this).load(R.drawable.headpic).override(200,200).into(head_image)
        user_id.text = "可达鸭"
    }

    private fun initGridView(){
        var giftList = ArrayList<Gift>()
        if (MyApplication.getInstance().waterNumber > 0) giftList.add(Gift(R.drawable.addwater,"水分","拥有数量"+MyApplication.getInstance().waterNumber+"mL"))
        if (MyApplication.getInstance().fertilizerNumber > 0) giftList.add(Gift(R.drawable.fertilize,"肥料","拥有数量*"+MyApplication.getInstance().fertilizerNumber))
        if (MyApplication.getInstance().flowers > 0) giftList.add(Gift(R.drawable.one_6,"水分","拥有数量*"+MyApplication.getInstance().flowers))
        grid_bag_view.adapter = GiftGridAdapter(this,giftList)
    }
}
