package com.example.subwaygame.activity

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.subwaygame.R
import com.example.subwaygame.data.ItemFailing
import kotlinx.android.synthetic.main.activity_collect.*
import kotlinx.android.synthetic.main.dialog_item.view.*

class CollectActivity : AppCompatActivity() {

    lateinit var dialog : AlertDialog
    companion object handler{
        var mHandler : Handler = Handler{false}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)
        initHandler()
        gameStart()
    }

    private fun initHandler(){
        handler.mHandler = Handler{
            if (it.what == 1){
                gameOver()
            }
            false
        }
    }

    private fun gameStart() {
        var lift = ItemFailing()
        if (ItemFailing.timeAll <= 0)
            ItemFailing.timeAll = 80
        ItemFailing.list.clear()
        ItemFailing.list.add(lift)
        collect_game.addView(lift)
        val th = Thread(lift)
        th.start()
    }

    private fun gameOver(){
        var inflater = LayoutInflater.from(application)
        var view = inflater.inflate(R.layout.dialog_item,null)
        view.hint_text.text = "已成功收集到水滴"
        view.hint_button.text = "领取"
        view.hint_button.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
            finish()
        })
        var builder=AlertDialog.Builder(this)
        builder.setView(view)
        dialog=builder.create()
        dialog.show()
    }
}
