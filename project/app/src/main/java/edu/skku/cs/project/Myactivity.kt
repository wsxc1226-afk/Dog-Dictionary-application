package edu.skku.cs.project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class myAdapter(val context: Context, val items: ArrayList<dogdata>,val db : dogdatabase, val ml : ListView): BaseAdapter() {


    override fun getCount(): Int {
        return items.size

    }

    override fun getItem(p0: Int): Any {
        return items.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.my_entry, null)
        val mt = view.findViewById<TextView>(R.id.mytextView)
        val mb = view.findViewById<Button>(R.id.mybutton)
        val mdb = view.findViewById<Button>(R.id.delete)
        mt.text = items.get(p0).name
        mb.setOnClickListener {
            val intent3= Intent(context, Dogactivity ::class.java).apply{
                putExtra("dname",items.get(p0).name)
                putExtra("dweight", items.get(p0).weight.toString())
                putExtra("dheight", items.get(p0).height.toString())
                putExtra("bg",items.get(p0).breed_group.toString())
                putExtra("ls",items.get(p0).life_span.toString())
                putExtra("tp",items.get(p0).temperament.toString())
                putExtra("imgurl",items.get(p0).imageurl.toString())
            }
            context.startActivity(intent3)
        }
        mdb.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                // 데이터베이스에 데이터 삽입
                val d= dogdata(items.get(p0).id,
                    items.get(p0).weight.toString(),
                    items.get(p0).height.toString(),
                    items.get(p0).name.toString(),
                    items.get(p0).breed_group.toString(),
                    items.get(p0).life_span.toString(),
                    items.get(p0).temperament.toString(),
                    items.get(p0).imageurl.toString()
                )

                if(db.dogDao().getAll().contains(d)==true) {
                    db.dogDao().deletedoglist(d)
                }
                val dl = db.dogDao().getAll()
                withContext(Dispatchers.Main) {
                    val madapter = myAdapter(context, dl as ArrayList<dogdata>,db,ml)
                    ml.adapter = madapter
                }
            }

        }
        return view
    }
}

class Myactivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_myactivity)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val myApp = application as myapp
        var db = myApp.db
        val ml = findViewById<ListView>(R.id.mymylist)
        CoroutineScope(Dispatchers.IO).launch {
            val dl = db.dogDao().getAll()
            withContext(Dispatchers.Main) {
                val madapter = myAdapter(this@Myactivity, dl as ArrayList<dogdata>,db,ml)
                ml.adapter = madapter
            }
        }
      //  val madapter = myAdapter(this@Myactivity, dl as ArrayList<dogdata>)
      //  ml.adapter = madapter
    }
}