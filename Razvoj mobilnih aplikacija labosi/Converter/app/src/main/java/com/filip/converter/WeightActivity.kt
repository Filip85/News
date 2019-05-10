package com.filip.converter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_weight.*
import kotlin.math.roundToInt

class WeightActivity : AppCompatActivity() {
    lateinit var spinnWeight1: String
    lateinit var spinnWeight2: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)

        setUpUi()
    }

    private fun setUpUi() {
        ArrayAdapter.createFromResource(this, R.array.unitsOfMass, android.R.layout.simple_spinner_item).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1Weight.adapter = arrayAdapter
            spinner2Weight.adapter = arrayAdapter
        }

        spinner1Weight.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnWeight1 = parent?.getItemAtPosition(position).toString()
            }

        }

        spinner2Weight.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnWeight2 = parent?.getItemAtPosition(position).toString()
            }
        }

        weightButton.setOnClickListener{ calculateAndNavgateToSecondWeightActivity() }
    }

    private fun calculateAndNavgateToSecondWeightActivity() {
        var res: Double
        val entered: String = enterText.text.toString()
        val navigate3 = Intent(this, ResActivity::class.java)
        if(spinnWeight1 == "Kilogram" && spinnWeight2 == "Pound"){
            res = enterText.text.toString().toDouble() * 2.2046
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE, entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Kilogram:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Pound:")
            startActivity(navigate3)
        }
        if(spinnWeight1 == "Kilogram" && spinnWeight2 == "Stone"){
            res = enterText.text.toString().toDouble() * 0.15747
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE, entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Kilogram:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Stone:")
            startActivity(navigate3)
        }
        if(spinnWeight1 == "Pound" && spinnWeight2 == "Kilogram"){
            res = enterText.text.toString().toDouble() / 2.2046
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE, entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Pound:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Kilogram:")
            startActivity(navigate3)
        }
        if(spinnWeight1 == "Pound" && spinnWeight2 == "Stone"){
            res = enterText.text.toString().toDouble() * 0.071429
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE, entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Pound:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Stone:")
            startActivity(navigate3)
        }
        if(spinnWeight1 == "Stone" && spinnWeight2 == "Kilogram"){
            res = enterText.text.toString().toDouble() / 0.15747
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE, entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Stone:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Kilogram:")
            startActivity(navigate3)
        }
        if(spinnWeight1 == "Stone" && spinnWeight2 == "Pound"){
            res = enterText.text.toString().toDouble() / 0.071429
            navigate3.putExtra(ResActivity.CONVERTED_VALUE, res.toString())
                    .putExtra(ResActivity.ENTERED_VALUE, entered)
                    .putExtra(ResActivity.FIRST_SPINNER, "Stone:")
                    .putExtra(ResActivity.SECOND_SPINNER, "Pound:")
            startActivity(navigate3)
        }
    }
}
