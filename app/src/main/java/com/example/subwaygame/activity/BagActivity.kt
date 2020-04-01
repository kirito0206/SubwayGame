package com.example.subwaygame.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.subwaygame.MyApplication
import com.example.subwaygame.R
import com.example.subwaygame.adapter.GiftGridAdapter
import com.example.subwaygame.data.Gift
import kotlinx.android.synthetic.main.activity_bag.*

class BagActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bag)
        var giftList = ArrayList<Gift>()
        if (MyApplication.getInstance().waterNumber > 0) giftList.add(Gift(R.drawable.addwater,"水分","拥有数量"+MyApplication.getInstance().waterNumber+"mL"))
        if (MyApplication.getInstance().fertilizerNumber > 0) giftList.add(Gift(R.drawable.fertilize,"肥料","拥有数量*"+MyApplication.getInstance().fertilizerNumber))
        if (MyApplication.getInstance().flowers > 0) giftList.add(Gift(R.drawable.one_6,"水分","拥有数量*"+MyApplication.getInstance().flowers))

        grid_bag_view.adapter = GiftGridAdapter(this,giftList)
    }
}
