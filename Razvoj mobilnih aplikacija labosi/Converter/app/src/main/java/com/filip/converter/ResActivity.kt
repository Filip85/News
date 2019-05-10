package com.filip.converter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_res.*

class ResActivity : AppCompatActivity() {

    companion object {
        const val FIRST_SPINNER = "first"
        const val ENTERED_VALUE = "value"
        const val SECOND_SPINNER = "second"
        const val CONVERTED_VALUE = "time"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res)

        setUpUi()
    }

    private fun setUpUi() {
        FirstSpinner.text = intent?.getStringExtra(FIRST_SPINNER ?: "nothing recieved")
        EnteredValue.text = intent?.getStringExtra(ENTERED_VALUE ?: "nothing recieved")
        SecondSpinner.text = intent?.getStringExtra(SECOND_SPINNER ?: "nothing recieved")
        ResultValue.text = intent?.getStringExtra(CONVERTED_VALUE ?: "nothing recieved")
    }
}
