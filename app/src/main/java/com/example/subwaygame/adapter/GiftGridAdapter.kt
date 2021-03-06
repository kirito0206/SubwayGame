package com.example.subwaygame.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.subwaygame.MyApplication
import com.example.subwaygame.R
import com.example.subwaygame.data.Gift
import kotlinx.android.synthetic.main.item_grid_layout.*

class GiftGridAdapter(val context: Context, private var giftList: MutableList<Gift>?): BaseAdapter() {

    // 单元格的 View
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (convertView == null) {
            var view = LayoutInflater.from(context).inflate(R.layout.item_grid_layout, null)
            //我猜这个函数的作用是指定这个类所对应的小毛驴文件
            var holder = ViewHolder()
            holder.desc = view.findViewById(R.id.grid_description)
            holder.pic = view.findViewById(R.id.grid_image)
            holder.name = view.findViewById(R.id.grid_name)
            view.tag = holder

            var gift = giftList?.get(position)
            if (gift != null) {
                //holder.pic.setImageResource(gift.picture)
                Glide.with(MyApplication.getContext()).load(gift.picture).override(600,200).into(holder.pic)
                holder.desc.text = gift.description
                holder.name.text = gift.name
            }
            return view
        } else {
            return convertView
        }
    }

    // 要渲染的单元格数量
    override fun getCount(): Int {
        if (giftList == null)
            return 0
        return giftList!!.size
    }

    inner class ViewHolder {
        lateinit var name: TextView
        lateinit var pic: ImageView
        lateinit var desc : Button
    }

    // 在这个示例中不用，Android 要求实现此方法
    override fun getItem(position: Int): Any? {
        return null
    }

    // 在这个示例中不用，Android 要求实现此方法
    override fun getItemId(position: Int): Long {
        return 0
    }
}