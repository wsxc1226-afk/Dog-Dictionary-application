package edu.skku.cs.project


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView

class Introduceactivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_introduce)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(R.id.navigationView)
        val linearlayout =navigationView.findViewById<LinearLayout>(R.id.linear)

        val nt =findViewById<TextView>(R.id.textView2)


        val i = intent.getStringExtra(MainActivity.im)
        val n = intent.getStringExtra(MainActivity.nick)
        val myApp = application as myapp
        if (i != null) {
            myApp.ii = i
            if (n != null) {
                myApp.nn = n
            }
        }
        val nnn =myApp.nn

        nt.text = "Hello $nnn!\n This is the small page that let you know popular dogs in the world!"




        //val n = intent.getStringExtra(MainActivity.nick)
        //val i = intent.getStringExtra(MainActivity.im)
        val g= intent.getStringExtra(MainActivity.gen)
        val u = findViewById<TextView>(R.id.breed)
        val e = findViewById<TextView>(R.id.explain)
        val di = findViewById<ImageView>(R.id.imageView2)
        val de = findViewById<TextView>(R.id.textView8)
        if(g.toString()=="male"){
            val dogBreeds = arrayOf("german shepherd", "golden retriever", "bulldog")
            val randomBreed = dogBreeds.random()
            val breedImageResource = when (randomBreed) {
                "german shepherd" -> R.drawable.germanshepherd;
                "golden retriever" -> R.drawable.goldenretriever
                "bulldog" -> R.drawable.bulldog
                "siberian husky" -> R.drawable.siberianhusky
                else -> {
                    R.drawable.bulldog
                }
            }
            di.setImageResource(breedImageResource as Int)
            when(randomBreed){
                "german shepherd" -> de.text="How about german shepherd?\nIt is very cute dog"
                "golden retriever" -> de.text="How about golden retriever?\nIt is very cute dog"
                "bulldog" -> de.text="How about bulldog?\nIt is very cute dog"
                "siberian husky" -> de.text="How about siberian husky?\nIt is very cute dog"
            }
        }
        else if(g.toString()=="female"){
            val dogBreeds = arrayOf("french ulldog", "poodle", "cavalier king charles spaniel")
            val randomBreed = dogBreeds.random()
            val breedImageResource = when (randomBreed) {
                "french bulldog" -> R.drawable.frenchbulldog
                "poodle" -> R.drawable.poodle
                "maltese" -> R.drawable.maltese
                "shih tzu" -> R.drawable.shihtzu
                "cavalier king charles spaniel" -> R.drawable.spaniel
                else -> {
                    R.drawable.bulldog
                }
            }
            di.setImageResource(breedImageResource as Int)
            when(randomBreed){
                "french bulldog" -> de.text="How about german french bulldog?\nIt is very cute dog"
                "poodle" -> de.text="How about poodle?\nIt is very cute dog"
                "maltese" -> de.text="How about maltese?\nIt is very cute dog"
                "shih tzu" -> de.text="How about siberian husky?\nIt is very cute dog"
                "cavalier king charles spaniel" -> de.text="How about cavalier king charles spaniel?\nIt is very cute dog"
            }
        }
        else{
            val dogBreeds = arrayOf("frenchbulldog", "poodle",  "spaniel","germanshepherd", "goldenretriever", "bulldog")
            val randomBreed = dogBreeds.random()
            val breedImageResource = when (randomBreed) {
                "french bulldog" -> R.drawable.frenchbulldog
                "poodle" -> R.drawable.poodle
                "maltese" -> R.drawable.maltese
                "shih tzu" -> R.drawable.shihtzu
                "spaniel" -> R.drawable.spaniel
                "germanshepherd" -> R.drawable.germanshepherd
                "goldenretriever" -> R.drawable.goldenretriever
                "bulldog" -> R.drawable.bulldog
                "siberian husky" -> R.drawable.siberianhusky
                else -> {
                    R.drawable.bulldog
                }
            }
            di.setImageResource(breedImageResource as Int)
            when(randomBreed){

                "frenchbulldog" -> de.text="How about french bulldog?\nIt is very cute dog"
                "poodle" -> de.text="How about poodle?\nIt is very cute dog"
                "maltese" -> de.text="How about maltese?\nIt is very cute dog"
                "shih tzu" -> de.text="How about siberian husky?\nIt is very cute dog"
                "spaniel" -> de.text="How about cavalier king charles spaniel?\nIt is very cute dog"
                "germanshepherd" -> de.text="How about german shepherd?\nIt is very cute dog"
                "goldenretriever" -> de.text="How about golden retriever?\nIt is very cute dog"
                "bulldog" -> de.text="How about bulldog?\nIt is very cute dog"
                "siberian husky" -> de.text="How about siberian husky?\nIt is very cute dog"
                "spaniel" -> R.drawable.spaniel
            }
        }
        u.text= "Hello! $nnn"
        val imageView = findViewById<ImageView>(R.id.userimage)

        Glide.with(this)
            .load(myApp.ii)
            .into(imageView)
        val next = findViewById<Button>(R.id.next)

        next.setOnClickListener {
            val intent2 = Intent(this@Introduceactivity, Breedactivity ::class.java)
            startActivity(intent2)
        }
        val o = findViewById<Button>(R.id.open)
        o.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START);
        }
        val y = findViewById<Button>(R.id.youtube)
        y.setOnClickListener {
            val intent2 = Intent(this@Introduceactivity, youtubeactivity ::class.java)
            startActivity(intent2)
        }
    }

}