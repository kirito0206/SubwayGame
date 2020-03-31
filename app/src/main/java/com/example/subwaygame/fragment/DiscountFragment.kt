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


class DiscountFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_item, container, false)
        var giftList = ArrayList<Gift>()
        giftList.add(Gift("123","滴滴出行八折券",1))
        giftList.add(Gift("123","滴滴出行八折券",1))
        giftList.add(Gift("123","滴滴出行八折券",1))
        giftList.add(Gift("123","滴滴出行八折券",1))
        giftList.add(Gift("123","滴滴出行八折券",1))
        var gridView = view.findViewById<GridView>(R.id.grid_view)
        gridView.adapter = GiftGridAdapter(MyApplication.getContext(),giftList)
        return view
    }
}