package com.filip.tasky

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.all_tasks.*
import kotlinx.android.synthetic.main.new_category.*
import kotlinx.android.synthetic.main.new_task.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUi()
    }

    private fun setUpUi() {
        viewPager.adapter = FrgamentAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}
