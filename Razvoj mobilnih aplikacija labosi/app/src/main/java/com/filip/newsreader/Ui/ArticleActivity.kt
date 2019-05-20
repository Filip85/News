package com.filip.newsreader.Ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import com.filip.newsreader.FragmentAdapter.FragmentAdapter
import com.filip.newsreader.R
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    var id: String? = "null"
    var actionbar: ActionBar? = null

    companion object {
        const val ID = "null"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        viewPager.adapter = FragmentAdapter(supportFragmentManager)
        viewPager.setCurrentItem(2, true)

        getId()

        viewPager.setCurrentItem(id!!.toInt(), true)

        actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)

    }

    private fun getId() {

        id = intent?.getStringExtra(ID ?: "nothing recieved")

    }

    override fun onSupportNavigateUp(): Boolean {
        //actionbar!!.title = ""
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
