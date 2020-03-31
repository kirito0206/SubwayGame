package com.example.subwaygame.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.subwaygame.R
import com.example.subwaygame.data.ItemFailing
import kotlinx.android.synthetic.main.activity_collect.*

class CollectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect)
        gameStart()
    }

    private fun gameStart() {
        val lift = ItemFailing()
        ItemFailing.list.clear()
        ItemFailing.list.add(lift)
        collect_game.addView(lift)
        val th = Thread(lift)
        th.start()
    }
}
