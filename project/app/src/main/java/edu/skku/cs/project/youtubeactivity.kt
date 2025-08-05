package edu.skku.cs.project


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class youtubeactivity : AppCompatActivity() {
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
            setContentView(R.layout.activity_youtube)
            //supportActionBar?.setDisplayShowTitleEnabled(false)
            val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)


            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = "JJVFSWHZYjY"
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
            val b2 = findViewById<Button>(R.id.button2)
            b2.setOnClickListener {
                youTubePlayerView.getYouTubePlayerWhenReady(object : YouTubePlayerCallback {
                    override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.pause()
                    }
                })
                val intent4 = Intent(this, quizactivity::class.java)
                startActivity(intent4)
            }
        }
    }
