package com.filip.newsreader.Ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.filip.newsreader.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_single.*
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup

class SingleActivity : AppCompatActivity() {

    private var img: String = "null"
    private var news: String ="null"

    companion object {                   //objekt koji nam služ za pristupanje određenom sadržaju kojeg smo poslali iz klase NewsHolder
        const val PICTURE = "picture"
        const val TITLE = "title"
        const val TEXT = "text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        setUpUi()    //postavljenje UI-a
    }

    private fun setUpUi() {
        img = intent.getStringExtra(PICTURE ?: "nothing") //primanje url kojeg pohranjujemo u varijabli img te pomoću Picassa s tog url-a učitavamo sliku i prikazujemo je na ImageView-u (singleImage)
        Picasso.get()
                .load(img)
                .error(android.R.drawable.stat_notify_error)
                .into(singleImage)

        newsTitleSingle.text = intent?.getStringExtra(TITLE
                ?: "nothing recieved")   //prikaz naslova vijesti


        news = intent.getStringExtra(TEXT ?: "nothing")    //u varijablu news stavljamo url single vijesti koji smo posali preko intenta

        redTextFromUrl()    //funkcija za učitavnja odnosno prikaz određenog teksta
    }

    fun redTextFromUrl(){
        doAsync {                   //služi kako se ne bi odvijalo na glavnoj nitim, ako se to ne korsti crashat će se aplikacija
            val doc = Jsoup.connect(news).get() //stvaramo konekciju sa url-um te u varijablu doc trpamo cijeli HTML sadržaj pomoću metode get
            val show = doc.select("div[class=story-body__inner] p, div[id=story-body] p, div[class=vxp-media__summary] p")
            //pomoću select oderđeujemo što nam treba za prikaz, u našem slučaju samo paragrafi.
            //Unutar svakog divu, na kojem jem su postavljene ove gore klase, uzimamo paragraf
            // Zarez se ponaša kao OR operator.
            //To se sve sprema u varijablu show

            newsFromUrl.text = show.text()    //prikaz vijesti odnosno samo pragrafa web stranice
        }
    }
}