package com.filip.converter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_time.*
import kotlin.math.roundToInt

class TimeActivity : AppCompatActivity() {
    lateinit var spinnTime1: String
    lateinit var spinnTime2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)

        setUpUi()
    }

    private fun setUpUi() {
        ArrayAdapter.createFromResource(this, R.array.unitsOfTime, android.R.layout.simple_spinner_item).also { timeAdapter ->
            timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1Time.adapter = timeAdapter
            spinner2Time.adapter = timeAdapter
        }

        spinner1Time.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnTime1 = parent?.getItemAtPosition(position).toString()
            }
        }

        spinner2Time.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnTime2 = parent?.getItemAtPosition(position).toString()
            }
        }

        timeButton.setOnClickListener{ calculateAndNavgateToSecondTimeActivity() }
    }

    private fun calculateAndNavgateToSecondTimeActivity() {
        var res: Double
        val entered: String = enterTextTime.text.toString()
        val navigate3 = Intent(this, ResActivity::class.java)
        if (spinnTime1 == "Hours" && spinnTime2 == "Minutes") {
            res = (enterTextTime.text.toString().toDouble() * 60)
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Hours")
                    .putExtra(ResActivity.SECOND_SPINNER, "Minutes")
            startActivity(navigate3)
        }
        if (spinnTime1 == "Hours" && spinnTime2 == "Seconds") {
            res = (enterTextTime.text.toString().toDouble() * 3600)
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Hours")
                    .putExtra(ResActivity.SECOND_SPINNER, "Seconds")
            startActivity(navigate3)
        }
        if (spinnTime1 == "Minutes" && spinnTime2 == "Hours") {
            res = (enterTextTime.text.toString().toDouble() / 60)
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Minutes:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Hours:")
            startActivity(navigate3)
        }
        if (spinnTime1 == "Minutes" && spinnTime2 == "Seconds") {
            res = (enterTextTime.text.toString().toDouble() * 60)
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Minutes:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Seconds:")
            startActivity(navigate3)
        }
        if (spinnTime1 == "Seconds" && spinnTime2 == "Hours") {
            res = (enterTextTime.text.toString().toDouble() / 3600)
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Seconds:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Hours:")
            startActivity(navigate3)
        }
        if (spinnTime1 == "Seconds" && spinnTime2 == "Minutes") {
            res = (enterTextTime.text.toString().toDouble() / 60)
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Seconds:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Minutes:")
            startActivity(navigate3)

        }
    }
}
