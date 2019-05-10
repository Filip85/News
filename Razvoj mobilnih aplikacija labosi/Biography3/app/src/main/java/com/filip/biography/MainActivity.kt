package com.filip.biography

import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.FieldPosition

open class MainActivity : AppCompatActivity() {

    var images = arrayOf(R.drawable.download, R.drawable.swh1web, R.drawable.stevenjobs);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUi()
    }

    private fun setUpUi() {
        albertImageView.setImageResource(images[0]);
        stephenImageView.setImageResource(images[1]);
        steveImageView.setImageResource(images[2]);

        albertImageView.setOnClickListener { view -> alberQuote() }
        stephenImageView.setOnClickListener { view -> stephenQuote() }
        steveImageView.setOnClickListener { view -> steveQuote() }
    }

    private fun alberQuote() {
        val qoute: String= "Everybody is a genius. But if you judge a fish by its ability to climb a tree, it will live its whole life believing that it is stupid.";
        Toast.makeText(this, qoute, Toast.LENGTH_LONG).show()
    }

    private fun stephenQuote() {
        val qoute: String= "People won't have time for you if you are always angry or complaining."
        Toast.makeText(this, qoute, Toast.LENGTH_LONG).show()
    }

    private fun steveQuote() {
        val qoute: String= "Sometimes life hits you in the head with a brick. Don't lose faith."
        Toast.makeText(this, qoute, Toast.LENGTH_LONG).show()
    }
}
