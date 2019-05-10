package com.filip.converter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_distance.*
import kotlinx.android.synthetic.main.activity_weight.*
import kotlin.math.roundToInt

class DistanceActivity : AppCompatActivity() {
    lateinit var spinnDistance1: String
    lateinit var spinnDistance2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distance)

        setUpUi()
    }

    private fun setUpUi() {
        ArrayAdapter.createFromResource(this, R.array.unitsOfDistance, android.R.layout.simple_spinner_item).also { distanceAdapter ->
            distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1Distance.adapter = distanceAdapter
            spinner2Distance.adapter = distanceAdapter
        }

        spinner1Distance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnDistance1 = parent?.getItemAtPosition(position).toString()
            }
        }

        spinner2Distance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnDistance2 = parent?.getItemAtPosition(position).toString()
            }
        }

        distanceButton.setOnClickListener{ calculateAndNavgateToSecondDistanceActivity() }

    }

    private fun calculateAndNavgateToSecondDistanceActivity() {
        var s1: String
        var s2: String
        var entered: String = enterTextDistance.text.toString()
        var res : Double
        val navigate3 = Intent(this, ResActivity::class.java)
        if(spinnDistance1 == "Kilometers" && spinnDistance2 == "Miles"){
            res = enterTextDistance.text.toString().toDouble() * 0.62137
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE, entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Kilometers:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Miles:")
            startActivity(navigate3)
        }
        if(spinnDistance1 == "Miles" && spinnDistance2 == "Kilometers"){
            res = enterTextDistance.text.toString().toDouble() / 0.62137
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Miles:")
                    .putExtra(ResActivity.FIRST_SPINNER, "Kilometers:")
            startActivity(navigate3)
        }
    }
}
