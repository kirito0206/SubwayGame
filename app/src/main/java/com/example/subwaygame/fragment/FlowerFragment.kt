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

class FlowerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_item, container, false)
        var giftList = ArrayList<Gift>()
        giftList.add(Gift(R.drawable.one_6,"玫瑰花","需要鲜花*3"))
        giftList.add(Gift(R.drawable.one_6,"郁金香","需要鲜花*3"))
        giftList.add(Gift(R.drawable.one_6,"百合花","需要鲜花*3"))
        giftList.add(Gift(R.drawable.one_6,"海棠花","需要鲜花*3"))
        giftList.add(Gift(R.drawable.one_6,"水仙花","需要鲜花*3"))
        view.grid_view.adapter = GiftGridAdapter(MyApplication.getContext(),giftList)
        return view
    }
}