package com.filip.converter

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    lateinit var spinn1: String
    lateinit var spinn2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        setUpUi()
    }

    private fun setUpUi() {
        ArrayAdapter.createFromResource(this, R.array.unitsOfTemperature1, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1Temperature.adapter = adapter;
            spinner2Temperature.adapter = adapter
        }

        spinner1Temperature.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                textView.text = "non"

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinn1= parent?.getItemAtPosition(position).toString()

            }

        }

        spinner2Temperature.onItemSelectedListener = object :  AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinn2 = parent?.getItemAtPosition(position).toString()
            }
        }

        temperatureButton.setOnClickListener{ calculateAndNavgateToSecondActivity() }
    }

    private fun calculateAndNavgateToSecondActivity() {
        var res : Double
        val entered: String = enterText.text.toString()
        val navigate2 = Intent(this, ResActivity::class.java)
        if(spinn1 == "Celsius" && spinn2 == "Fahrenheit"){
            res = enterText.text.toString().toDouble() * 9/5 + 32
            navigate2.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Celsius:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Fahrenheit:")
            startActivity(navigate2)
        }
        if(spinn1 == "Celsius" && spinn2 == "Kelvin"){
            res = enterText.text.toString().toDouble() + 273.15
            navigate2.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Celsius:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Kelvin:")
            startActivity(navigate2)
        }
        if(spinn1 == "Kelvin" && spinn2 == "Celsius"){
            res = enterText.text.toString().toDouble() - 273.15
            navigate2.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Kelvin:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Celsius:")
            startActivity(navigate2)
        }
        if(spinn1 == "Kelvin" && spinn2 == "Fahrenheit"){
            res = enterText.text.toString().toDouble() * 9/5 - 459.67
            navigate2.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Kelvin:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Fahrenheit:")
            startActivity(navigate2)
        }
        if(spinn1 == "Fahrenheit" && spinn2 == "Celsius"){
            res = (enterText.text.toString().toDouble() - 32) * 5/9
            navigate2.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Fahrenheit:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Celsius:")
            startActivity(navigate2)
        }
        if(spinn1 == "Fahrenheit" && spinn2 == "Kelvin") {
            res = (enterText.text.toString().toDouble() + 459.67) * 5 / 9
            navigate2.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE,entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Fahrenheit:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Kelvin:")
            startActivity(navigate2)
        }




    }
}
