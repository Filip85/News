package com.filip.converter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUi()
    }

    private fun setUpUi() {

        pictureDisplay.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        pictureDisplay.itemAnimator = DefaultItemAnimator()
        pictureDisplay.addItemDecoration(DividerItemDecoration(this,RecyclerView.VERTICAL))
        displayData()
    }

    private fun displayData() {
        val navigateToWeight = Intent(this, WeightActivity::class.java)
        val navigateToTemperature = Intent(this, Main2Activity::class.java)
        val navigateToDistance = Intent(this, DistanceActivity::class.java)
        val navigateToTime = Intent(this, TimeActivity::class.java)

        val pictureListener = object: PictureInteractionListener{
            override fun newActivity(id: Int) {
                when(id){
                    1 -> startActivity(navigateToWeight)
                    2 -> startActivity(navigateToTemperature)
                    3 -> startActivity(navigateToDistance)
                    4 -> startActivity(navigateToTime)
                }
            }
        }

        pictureDisplay.adapter = PictureAdapter(PictureObject.pictures, pictureListener)
    }
}