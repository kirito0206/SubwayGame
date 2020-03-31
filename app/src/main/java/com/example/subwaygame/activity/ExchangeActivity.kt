package com.example.subwaygame.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.subwaygame.R
import com.example.subwaygame.fragment.DiscountFragment
import com.example.subwaygame.fragment.FlowerFragment
import com.example.subwaygame.fragment.SeedFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_exchange.*

class ExchangeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exchange)
        initViewPager()
        initTabViewPager()
    }

    private fun initViewPager(){
        view_pager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                //返回自己所需的页数
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                //返回自己所需的fragment
                return when(position){
                    0-> DiscountFragment()
                    1-> SeedFragment()
                    2-> FlowerFragment()
                    else -> Fragment()
                }
            }
        }

        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                Log.d("123", "onPageScrollStateChanged: $state")
            }

            override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                Log.d("123", "onPageScrolled：$position $positionOffset $positionOffsetPixels")
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // 很多情况下在页面切换完成后在此方法进行各种操作
                Log.i("123", "onPageSelected：$position")
            }
        })
    }

    private fun initTabViewPager() {
        TabLayoutMediator(tab_layout, view_pager,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    //根据position自行设置tab文本
                    when(position){
                        0->tab.text = "折扣券/优惠券"
                        1->tab.text = "实物种子"
                        2->tab.text = "实物鲜花"
                    }
                }
        ).attach()
    }
}
