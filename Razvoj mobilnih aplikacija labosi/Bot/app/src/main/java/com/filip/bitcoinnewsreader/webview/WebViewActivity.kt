package com.filip.bitcoinnewsreader.webview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.filip.bitcoinnewsreader.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    private var url: String = ""

    companion object {
        const val URL = "url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        setUpUi()
    }

    private fun setUpUi() {
        val url = intent?.getStringExtra(URL ?: "nothing recieved")
        WebView.loadUrl(url)
    }
}
