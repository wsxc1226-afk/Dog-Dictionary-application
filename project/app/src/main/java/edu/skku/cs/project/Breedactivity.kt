package edu.skku.cs.project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.*
import java.io.IOException

data class Breed(val weight: Weightinfo?=null,
                 val height: Heightinfo?=null,
                 val id: Int,
                 val name: String?=null,
                 val breed_group: String?=null,
                 val life_span: String?=null,
                 val temperament: String?=null,
                 val image: Imageinfo?=null){
    data class Weightinfo(val imperial: String?, val metric: String?)
    data class Heightinfo(val imperial: String?, val metric: String?)
    data class Imageinfo(val url:String?)
    var ischecked : Boolean = false
}

class BreedAdapter(val context: Context, val items: ArrayList<Breed>, var db : dogdatabase): BaseAdapter() {
    var ilist = ArrayList<Breed>()
    var bundle = Bundle()

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
        val view: View = inflater.inflate(R.layout.dog_entry, null)

        val dn = view.findViewById<TextView>(R.id.dogname)
        val dsb = view.findViewById<Button>(R.id.dogstartbutton)


        dn.text= items.get(p0).name.toString()

        dsb.setOnClickListener {
            val intent3= Intent(context, Dogactivity ::class.java).apply{
                putExtra("dname",items.get(p0).name.toString())
                putExtra("dweight", items.get(p0).weight?.metric.toString())
                putExtra("dheight", items.get(p0).height?.metric.toString())
                putExtra("bg",items.get(p0).breed_group.toString())
                putExtra("ls",items.get(p0).life_span.toString())
                putExtra("tp",items.get(p0).temperament.toString())
                putExtra("imgurl",items.get(p0).image?.url.toString())
            }
            Log.d("sss", items.get(p0).weight?.metric.toString())
            Log.d("sss", items.get(p0).height?.metric.toString())
            Log.d("sss", items.get(p0).breed_group.toString())
            context.startActivity(intent3)
        }
        val checkBox: CheckBox = view.findViewById(R.id.checkbox)
        if(items.get(p0).ischecked){
            checkBox.isChecked = true
            Log.d("asdf","why check box not work")
        }
        else{
            checkBox.isChecked=false
        }
        checkBox.isChecked = items.get(p0).ischecked
        checkBox.setOnClickListener {
            items.get(p0).ischecked = checkBox.isChecked
            if (checkBox.isChecked) {
                CoroutineScope(Dispatchers.IO).launch {
                    // 데이터베이스에 데이터 삽입
                    val d= dogdata(items.get(p0).id,
                        items.get(p0).weight?.metric.toString(),
                        items.get(p0).height?.metric.toString(),
                        items.get(p0).name.toString(),
                        items.get(p0).breed_group.toString(),
                        items.get(p0).life_span.toString(),
                        items.get(p0).temperament.toString(),
                        items.get(p0).image?.url.toString()
                    )
                    if(db.dogDao().getAll().contains(d)==false) {
                        d.checked =true
                        db.dogDao().insertdoglist(d)
                        Log.d("asdf",d.toString())
                    }
                }

            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    // 데이터베이스에 데이터 삽입
                    val d= dogdata(items.get(p0).id,
                        items.get(p0).weight?.metric.toString(),
                        items.get(p0).height?.metric.toString(),
                        items.get(p0).name.toString(),
                        items.get(p0).breed_group.toString(),
                        items.get(p0).life_span.toString(),
                        items.get(p0).temperament.toString(),
                        items.get(p0).image?.url.toString()
                    )
                    if(db.dogDao().getAll().contains(d)==true) {
                        d.checked =false
                        db.dogDao().deletedoglist(d)
                        Log.d("asdf",d.toString())
                    }
                }

            }
        }
        return view
    }
}
//val id="264"
//val host = "https://api.thedogapi.com/v1/breeds/$id"

class Breedactivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_breed)
        var intarray = IntArray(500)
        val breedView = findViewById<ListView>(R.id.breedlist)
        val b = findViewById<Button>(R.id.renew)
        val client = OkHttpClient()
        val id=16
        var breedlist = mutableListOf<Breed>()
        val myApp = application as myapp
        var db = myApp.db
        var count = 0
        val sb = findViewById<Button>(R.id.searchtype)
        val searchView = findViewById<SearchView>(R.id.searchview)
        sb.setOnClickListener {
            count++;
            when(count%4){
                0->sb.text="NAME SEARCH"
                1->sb.text="WEIGHT SEARCH"
                2->sb.text="HEIGHT SEARCH"
                3->sb.text="BREED SEARCH"
            }
            searchView.setQuery("", false)
            searchView.clearFocus()
            val bAdapter = BreedAdapter(this@Breedactivity, breedlist as ArrayList<Breed>,db)
            breedView.adapter = bAdapter
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
       /* CoroutineScope(Dispatchers.IO).launch {
            db.dogDao().deleteAll()
        }*/

        val host = "https://api.thedogapi.com/v1/breeds?limit=10&page="
        //key =4MLsHQWB23RzLYMU7CHnlTUltU0wPXQ4
        CoroutineScope(Dispatchers.Main).launch {
            for (i in 0..16) {
                searchView.requestFocus()
                val req = Request.Builder().url(host + i.toString()).build()

                val response = withContext(Dispatchers.IO) {
                    client.newCall(req).execute()
                }

                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val str = response.body!!.string()
                val typeToken = object : TypeToken<List<Breed>>() {}.type
                breedlist = (breedlist + Gson().fromJson<List<Breed>>(str, typeToken)) as MutableList<Breed>
                val bAdapter = BreedAdapter(this@Breedactivity, breedlist as ArrayList<Breed>,db)
                breedView.adapter = bAdapter
            }


            searchView.requestFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        /*filteredList = originalList.filter { item ->
                            item.contains(newText, ignoreCase = true)*/
                        var filtered_breedlist: List<Breed> = ArrayList()
                        for(i in 0.. breedlist.size-1){
                            if(count%4==0) {
                                if(newText==""){
                                    filtered_breedlist =  breedlist
                                    break
                                }
                                if (breedlist[i].name?.lowercase()
                                        ?.contains(newText.lowercase()) == true
                                ) {
                                    filtered_breedlist = filtered_breedlist + breedlist[i]
                                }
                            }
                            else if(count%4==1){
                                if (newText.toDoubleOrNull() ==null) {
                                    continue
                                }
                                if(!breedlist[i].weight?.metric.toString().contains("-")){
                                    continue
                                }
                                if(newText==""){
                                    filtered_breedlist =  breedlist
                                    break
                                }
                                val data = breedlist[i].weight?.metric.toString()
                                val numbers = data.split("-").map { it.trim().toDouble() }
                                val firstNumber = numbers[0]
                                val secondNumber = numbers[1]

                                if(newText.toDouble() in firstNumber..secondNumber){
                                    filtered_breedlist = filtered_breedlist + breedlist[i]
                                }
                            }
                            else if(count%4==2){
                                if (newText.toDoubleOrNull() ==null) {
                                    continue
                                }
                                if(!breedlist[i].height?.metric.toString().contains("-")){
                                    continue
                                }
                                if(newText==""){
                                    filtered_breedlist =  breedlist
                                    break
                                }
                                val data = breedlist[i].height?.metric.toString()
                                val numbers = data.split("-").map { it.trim().toDouble() }
                                val firstNumber = numbers[0]
                                val secondNumber = numbers[1]
                                if(newText.toDouble() in firstNumber..secondNumber){
                                    filtered_breedlist = filtered_breedlist + breedlist[i]
                                }
                            }
                            else{
                                if(newText==""){
                                    filtered_breedlist =  breedlist
                                    break
                                }
                                if(breedlist[i].breed_group?.lowercase()?.contains(newText.lowercase()) == true){
                                    filtered_breedlist = filtered_breedlist + breedlist[i]
                                }
                            }
                        }
                        val bAdapter = BreedAdapter(this@Breedactivity, filtered_breedlist as ArrayList<Breed>,db)
                        breedView.adapter = bAdapter

                    }
                    return true
                }
            })
        }
    b.setOnClickListener {
        val intent4 = Intent(this, Myactivity::class.java)
        startActivity(intent4)
    }

    val rb =findViewById<Button>(R.id.button)
        rb.tooltipText="If you delete somethings in my list,\nyou can renew checkbox by this button!"
   rb.setOnClickListener {
        CoroutineScope(Dispatchers.IO).launch {
            val dl = db.dogDao().getAll()
            for(i in 0..breedlist.size-1){
                breedlist[i].ischecked=false
            }
            for(i in 0..dl.size-1){
                for(j in 0..breedlist.size-1){
                    if(dl[i].name==breedlist[j].name){
                        breedlist[j].ischecked= true
                        Log.d("asdf", " for statement work?")
                    }
                }
            }
            withContext(Dispatchers.Main) {
                val rAdapter = BreedAdapter(this@Breedactivity, breedlist as ArrayList<Breed>,db)
                breedView.adapter = rAdapter
            }
        }

    }


    }
}