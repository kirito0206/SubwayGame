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


class DiscountFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_item, container, false)
        var giftList = ArrayList<Gift>()
        giftList.add(Gift(R.drawable.water,"地铁出行八折券","需要鲜花*1"))
        giftList.add(Gift(R.drawable.water,"快车出行八折券","需要鲜花*1"))
        giftList.add(Gift(R.drawable.water,"KFC八折券","需要鲜花*1"))
        giftList.add(Gift(R.drawable.water,"金拱门八折券","需要鲜花*1"))
        giftList.add(Gift(R.drawable.water,"星巴克八折券","需要鲜花*1"))
        view.grid_view.adapter = GiftGridAdapter(MyApplication.getContext(),giftList)
        return view
    }
}