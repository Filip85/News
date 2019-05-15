package com.filip.newsreader.Ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.filip.newsreader.Database.NewsDatabase
import com.filip.newsreader.DatabaseTable.Articles
import com.filip.newsreader.DatabaseTable.News
import com.filip.newsreader.DatabaseTable.Time
import com.filip.newsreader.R
import com.filip.newsreader.Recylcer.RecylcerAdapter
import com.filip.newsreader.Retrofit.API_KEY
import com.filip.newsreader.Retrofit.RetrofitFactory
import com.filip.newsreader.Retrofit.SORT_BY
import com.filip.newsreader.Retrofit.SOURCE
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalTime


class MainActivity : AppCompatActivity(), Callback<News> {

    private var index: Int = 0
    private var flag: Boolean = false
    private var nisuPrviPodaci: Boolean = true
    private var checkApp: Boolean = false

    private var min: Int = 0

    private val newsAdapter by lazy { RecylcerAdapter() }   //instanciranje RecyclerAdaptera

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("onCreate", "onCreate se pokrenuo")

        checkDatabase()  //provjerava je li baza prazna ili puna

        setUpUi()   //metoda za postavljanje UI-a

        checkApp = true  //checkApp postavljamo u true kada se uđe u aplikaiju te se checkTime() može pozvati unutar handlera jer je aplikacija aktivna.

        checkTime()    //metoda za provjera vremena, je li prošlo 5 minuta
    }

    private fun setUpUi() {    //postavljanje recyclerView na ekran. TO se vrši pomoću adaptera
        news.layoutManager = LinearLayoutManager(this)
        news.itemAnimator = DefaultItemAnimator()
        news.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        news.adapter = newsAdapter    //postavljanje recyclerView pomoću adaptera

    }

    private fun checkDatabase(){   //funkcija kojom se provjera je li baza puna ili prazna
        if(NewsDatabase.newsDao.countArticles() == 0){  //provjera je li baza prazna. Ako je, pokreni retrofit
            callRetrofit() //pokretanje retofita

            min = LocalTime.now().minute                         //u min se stavlja trenutno vrijeme i pohranjuje u bazu
            var time: Time = Time(1, min)                //stvaranje objekta klase Time
            NewsDatabase.newsDao.insertTime(time)   //umetanje trenutnog vremena u bazu

            nisuPrviPodaci = false  //sada vec postoje podaci u bazi pa postavljamo  u false

        }
        else if(NewsDatabase.newsDao.countArticles() != 0 && nisuPrviPodaci){  //baza nije prazna. nisuPrviPodaci služi kao osigurač da se odmah poslje prvog uvjeta ne pokrene i kod unutar ovog drugog. Ova će se funkcija pokrenuti ako se opet uđi u aplikaciju, a postoje podaci u bazi.
            newsAdapter.show(NewsDatabase.newsDao.getAllArticles())            //ispis članaka, odnosno naslova, opisa i postavljenje slike iz baze u recyclerView
            min = NewsDatabase.newsDao.getTime()                         //spremanje vremena, u varjabli min, iz baze kako bi se dalje u checku moglo provjeravati
            Log.d("NisuPrviPodaci", "Nisu Prvi Podaci")
            loading.visibility = View.GONE   //da nam se loader ne vrti prilikom ponovog ulaska u aplikaciju
        }
    }

    private fun checkTime() {    //metoda koja pimoću handlera provjerava jesu li podaci u bazi stariji od 5 minuta
        Log.d("TAG5", "check")
        Handler().postDelayed({                      //handler koji se pokreće svakih 10 sekundi
            var jeLiProsloPetMinuta = LocalTime.now().minute //hvatanje trenutnog vremena (samo minute)

            if((min - jeLiProsloPetMinuta) <=-5 || (min - jeLiProsloPetMinuta) >= 5){    //provjera je li prošlo 5 minuta
                callRetrofit()  //pokretanje retorfita

                NewsDatabase.newsDao.updateTime(jeLiProsloPetMinuta)     //spremanje zadnjeg vremena u bazu, uvjek na mjesto gdje je id 1 jer nam treba samo zadnje vrijeme
                min = NewsDatabase.newsDao.getTime()    //to zadnje vrijeme se sada pohranjuje u varijablu min kao "referntna točka" za usporedbu je li prošlo 5 minuta

                flag = true   //kako se ne bi pokrenuo if kojim provjeravamo je li baza prazna i jesu li to prvi podaci (1. if)
                Log.d("TAG1", "pokrenuo se retrofit")
            }

            if(checkApp){         //ako je ckeckApp onda opet pozovi checkTime()
                checkTime()
            }

        }, 10000)   //pokretanje handlera svakih 10 sekundi
    }

    private fun callRetrofit(){
        RetrofitFactory.showNewsFromBBC                   //pokretanje retrofita
                .showNews(SOURCE, SORT_BY, API_KEY)
                .enqueue(this)
    }

    override fun onFailure(call: Call<News>, t: Throwable) {

        ErrorDialog()
    }

    override fun onResponse(call: Call<News>, response: Response<News>) {
        loading.visibility = View.VISIBLE    //pokretanje loadera

        val results = response.body()                        //rezultata dohvačanja podatka sa api-ja

        val articlesResults = results!!.articles             //spremanje samo liste articala (članaka) u articlesResults, što nam zapravi i treba

        insertInBaseAndDisplayData(articlesResults)    //funkcija za spremnaje  podataka u bazu i ispis tih podataka iz baze u recyclerView

        loading.visibility = View.GONE        //zaustavljanje loadera
    }

    private fun insertInBaseAndDisplayData(articlesResults: List<Articles>){
        if(NewsDatabase.newsDao.countArticles() == 0){   //ako je baza prazna pohranjuju se prvi podaci u bazu
            for(article in articlesResults){  //prolazi se kroz sve article u listi
                val articleRes = Articles(index, article.title, article.description, article.url, article.urlToImage) //kreiranje objekta tipa Articles
                NewsDatabase.newsDao.insertArticles(articleRes)  //spremanje articlesRes u bazu (spremanje indexa odnosno id-a, naslova, opisa, url vijesti i url-a slike
                index++            //povečavanje id-a
            }
            index = 0  //stavljanje varijable id na 0 kako bi se nekada u budučnosti mogli obrisati iz baze. Bit će nam potreban.
            Log.d("TAG2", "Prvi podaci u bazi")
            newsAdapter.show(NewsDatabase.newsDao.getAllArticles())  //postavljanje recyclerView-a
        }
        else if(flag && NewsDatabase.newsDao.countArticles() != 0){   //ako je flag true i baza nije prazna, obriši podatke iz baze i stavi nove. To se moglo učiniti i pomoću update, a ne delete.
            for(article in articlesResults) {    //isto prolazak kroz aricle u listi kao i u uvjetu prije
                val articleRes = Articles(index, article.title, article.description, article.url, article.urlToImage)
                NewsDatabase.newsDao.deleteAllArticles(articleRes)   //brisanje članaka iz baze
                index++
            }
            index = 0  //postavljanje indeksa na 0 kako bi se mogli pohraniti novi podaci sa id od 1 do 10

            for(article in articlesResults){
                val articleRes = Articles(index, article.title, article.description, article.url, article.urlToImage)
                NewsDatabase.newsDao.insertArticles(articleRes)  //umatanje novih podataka u bazu
                index++
            }
            index = 0
            Log.d("TAG3", "Novi podaci u bazi")
            newsAdapter.show(NewsDatabase.newsDao.getAllArticles())  //postavljanje recyclerView-a
        }
        else{
            newsAdapter.show(NewsDatabase.newsDao.getAllArticles())  //postavljenj recyclerView-a
        }

    }

    private fun ErrorDialog(){   //dialog koji će izbaciti alert ako je došlo do greške prilikom povlačenja podataka sa api-ja
        AlertDialog.Builder(this)    //"izgradnja" altert dialoga
                .setTitle(R.string.title)  //naslov dialoga odnsno tog ekrančića koji će iskočiti
                .setMessage(R.string.message)          //poruka koja će pisati u tom prozorčiću
                .setPositiveButton(R.string.positive_button) { dialog, which ->
                    Log.d("TAG10", "Pritisnut Uredu")   //na positive bitton se ispisuje preko log-a da je pritinsit uredu
                }
                .create()   //kreiranje dialoga
                .show() //prikaz dialoga
    }

    override fun onStart() {          //kada se sa SingleActivity-a vratimo na MainActivity
        super.onStart()
        if(!checkApp){    //provjera je li se korisnik vratio na main activity, ako je pokrećemo retrofit
            checkTime()
            checkApp = true
        }
        Log.d("Start", "onStart")
    }

    override fun onPause() {  // kada se izađe iz aplikacije i opet uđe onda se provjeri checkApp koji
        super.onPause()
        checkApp = false                      //spriječavamo da se checkTime() unutar handlera pozove kada aplikacija nije aktivna. Time smo spriječili dva puta istovremeni poziv funckije unutar handlera.
        Log.d("onPause", "Pause")

    }
}


