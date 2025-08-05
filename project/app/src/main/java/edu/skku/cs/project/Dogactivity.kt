package edu.skku.cs.project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class Dogactivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item0 -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_item1 -> {
                val intent = Intent(this, Introduceactivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_item2 -> {
                val intent = Intent(this, Breedactivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_item3 -> {
                val intent = Intent(this,Myactivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_item4 ->{
                val intent = Intent(this,youtubeactivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_item5 ->{
                val intent = Intent(this,quizactivity::class.java)
                startActivity(intent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val n = intent.getStringExtra("dname")
        val w = intent.getStringExtra("dweight")
        val h =intent.getStringExtra("dheight")
        val bg = intent.getStringExtra("bg")
        val ls = intent.getStringExtra("ls")
        val tp = intent.getStringExtra("tp")
        val i= intent.getStringExtra("imgurl")
        val di =findViewById<ImageView>(R.id.dogimage)
        val dn =findViewById<TextView>(R.id.dname)
        dn.text= "$n"
        val dw =findViewById<TextView>(R.id.dweight)
        dw.text="$w"+"kg"
        val dh =findViewById<TextView>(R.id.dheight)
        dh.text="$h"+"cm"
        val dbg =findViewById<TextView>(R.id.dbreedgroup)
        dbg.text="$bg"
        val dls =findViewById<TextView>(R.id.dlifespan)
        dls.text="$ls"
        val dtp =findViewById<TextView>(R.id.dtemperament)
        dtp.text="$tp"
        Glide.with(this)
            .load(i)
            .into(di)

    }
}