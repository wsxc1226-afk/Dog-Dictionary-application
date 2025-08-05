package edu.skku.cs.project

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity


class quizactivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_quiz)
        val t1 = findViewById<CheckBox>(R.id.t1)
        val t2 = findViewById<CheckBox>(R.id.t2)
        val t3 = findViewById<CheckBox>(R.id.t3)
        val t4 = findViewById<CheckBox>(R.id.t4)
        val t5 = findViewById<CheckBox>(R.id.t5)
        val f1 = findViewById<CheckBox>(R.id.f1)
        val f2 = findViewById<CheckBox>(R.id.f2)
        val f3 = findViewById<CheckBox>(R.id.f3)
        val f4 = findViewById<CheckBox>(R.id.f4)
        val f5 = findViewById<CheckBox>(R.id.f5)
        val b = findViewById<Button>(R.id.checkanswer)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        b.setOnClickListener {
            if(t1.isChecked&&!f1.isChecked&&f2.isChecked&&!t2.isChecked&&t3.isChecked&&!f3.isChecked&&
                t4.isChecked&&!f4.isChecked&&f5.isChecked&&!t5.isChecked){
                Toast.makeText(applicationContext, "YOU GOT THE ANSWER!!", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(applicationContext, "OOPS TRY AGAIN!!", Toast.LENGTH_SHORT).show()
            }
            t1.isChecked=false
            t2.isChecked=false
            t3.isChecked=false
            t4.isChecked=false
            t5.isChecked=false
            f1.isChecked=false
            f2.isChecked=false
            f3.isChecked=false
            f4.isChecked=false
            f5.isChecked=false

        }

    }
}