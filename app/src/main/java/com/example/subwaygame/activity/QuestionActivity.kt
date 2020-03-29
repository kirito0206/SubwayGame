package com.example.subwaygame.activity

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.subwaygame.R
import com.example.subwaygame.data.Questions
import com.example.subwaygame.retrofit.SubwayGameService
import kotlinx.android.synthetic.main.activity_question.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class QuestionActivity : AppCompatActivity() {

    private var questionText : String = ""
    private var answerText : String = ""
    private lateinit var mHandler : Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        initHandler()
        initChaptersWithRetrofit()
        initView()
    }

    private fun initView(){
        question_send.setOnClickListener(View.OnClickListener {
            if (answerText == question_edit.text.toString()){
                Toast.makeText(this,"恭喜你，回答正确！！",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "很遗憾，回答错误！！\n正确答案是:$answerText",Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initHandler(){
        mHandler = Handler{
            if (it.what == 1){
                question_text.text = questionText
            }
            false
        }
    }

    private fun initChaptersWithRetrofit() {
        var retrofit  = Retrofit.Builder()
                .baseUrl("https://api.tianapi.com/txapi/") //设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        //创建请求接口类
        var subwayGameService = retrofit.create(SubwayGameService::class.java)
        subwayGameService.getQuestion("db447e22744f453fd0607b78a30863c9").enqueue(object : retrofit2.Callback<Questions> {
            override fun onFailure(call: Call<Questions>, t: Throwable) {
                t.printStackTrace()
                Log.d("132",t.message+"")
            }

            override fun onResponse(call: Call<Questions>, response: Response<Questions>) {
                var questions : Questions? = response.body()
                if (questions != null && questions.msg == "success" && questions.newslist.isNotEmpty()) {
                    questionText = questions.newslist[0].quest
                    answerText = questions.newslist[0].result
                    Log.d("132",questionText+":"+answerText)
                }
                var message = Message()
                message.what = 1
                mHandler.sendMessage(message)
            }
        })
    }
}
