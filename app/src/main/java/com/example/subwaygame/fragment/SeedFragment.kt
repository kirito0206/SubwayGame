package com.example.subwaygame.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.subwaygame.MyApplication
import com.example.subwaygame.R
import com.example.subwaygame.adapter.GiftGridAdapter
import com.example.subwaygame.data.Gift
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.android.synthetic.main.fragment_item.view.*

class SeedFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_item, container, false)
        var giftList = ArrayList<Gift>()
        giftList.add(Gift(R.drawable.one_1,"郁金香种子","需要鲜花*2"))
        giftList.add(Gift(R.drawable.one_1,"薰衣草种子","需要鲜花*2"))
        giftList.add(Gift(R.drawable.one_1,"玫瑰花种子","需要鲜花*2"))
        giftList.add(Gift(R.drawable.one_1,"向日葵种子","需要鲜花*2"))
        giftList.add(Gift(R.drawable.one_1,"大白菜种子","需要鲜花*2"))
        view.grid_view.adapter = GiftGridAdapter(MyApplication.getContext(),giftList)
        return view
    }
}