package org.techtown.mysololife.contentsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.widget.Toast
import org.techtown.mysololife.R

class ContentShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_show)

        //ContentsListActivity에서 보낸 webUrl데이터를 받는다.
        val getUrl = intent.getStringExtra("url")
        Log.d("ContentShowActivity : ","getUrl"+getUrl)//잘 받아졌나 확인용.

        //웹뷰를 찾는다.
        val webView : WebView = findViewById(R.id.webView)
        //웹뷰안에 받아온 url을 집어넣는다.
        webView.loadUrl(getUrl.toString())
    }
}