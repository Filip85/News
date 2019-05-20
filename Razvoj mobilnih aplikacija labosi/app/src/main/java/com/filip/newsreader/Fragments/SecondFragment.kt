package com.filip.newsreader.Fragments

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.filip.newsreader.Cont.MyApplication
import com.filip.newsreader.Database.NewsDatabase
import com.filip.newsreader.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.first_fragment.*
import kotlinx.android.synthetic.main.second_fragment.*
import kotlinx.android.synthetic.main.tenth_fragment.*
import org.jetbrains.anko.doAsync
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class SecondFragment(): Fragment() {

    var isStarted = false;
    var actionbar: ActionBar? = null

    companion object {
        fun newInstance(): SecondFragment{
            return SecondFragment()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {
        val view = inflater.inflate(R.layout.second_fragment, container, false);

        redTextFromUrl()

        return view;
    }

    fun redTextFromUrl(){
        doAsync {                   //služi kako se ne bi odvijalo na glavnoj nitim, ako se to ne korsti crashat će se aplikacija
            val doc = Jsoup.connect(NewsDatabase.newsDao.getArticle(1)).get() //stvaramo konekciju sa url-um te u varijablu doc trpamo cijeli HTML sadržaj pomoću metode get
            val show = doc.select("div[class=story-body__inner] p, div[id=story-body] p, div[class=vxp-media__summary] p")
            //pomoću select oderđeujemo što nam treba za prikaz, u našem slučaju samo paragrafi.
            //Unutar svakog divu, na kojem jem su postavljene ove gore klase, uzimamo paragraf
            // Zarez se ponaša kao OR operator.
            //To se sve sprema u varijablu show

            showTextOfArticle(show)  //poziv funkcije koja nam služi za ispis teksta

            Picasso.get()
                    .load(NewsDatabase.newsDao.getImageOfArticle(1))
                    .into(articleImage2)
        }
    }

    fun showTextOfArticle(show: Elements){
        articleTitle2.text = NewsDatabase.newsDao.getTitleOfArticle(1).toString()
        articleText2.text = show.text()    //prikaz članka odnosno samo pragrafa web stranice
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {   //picasso mi nije radio unutart showTextOfArticle pa sam ga stavio ovdje
        super.onViewCreated(view, savedInstanceState)                       //odnosno kada se pozove metoda readTextFromUrl koja zove showTextOfArticle ne ucita se slika

        Picasso.get()
                .load(NewsDatabase.newsDao.getImageOfArticle(1))
                .into(articleImage2)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {  //morao sam staviti postavljanje naslova u setUserVisibleHint kako bi se na swap promjenio naslov
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser) {
            Handler().postDelayed({
                (activity as AppCompatActivity).supportActionBar?.title = NewsDatabase.newsDao.getTitleOfArticle(1)

            }, 500)  //bez malog delaya puca app. mislim da je to zato jer se bez delaya ne stigne posatviti cijeli view prije nego se postavi naslov actionbara
        }
    }
}