package edu.skku.cs.project

import android.app.Application
import androidx.room.Room
import com.kakao.sdk.common.KakaoSdk

class myapp : Application() {
    lateinit var db: dogdatabase
    var currentUserIdentifier: String = "" // 현재 사용자 식별자
     var ii: String = ""
     var nn: String = ""

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "ad51dd5d6297f331d7a10b3eba2b098f")
        db = createDatabase(currentUserIdentifier)
    }

    fun createDatabase(identifier: String): dogdatabase {
        return Room.databaseBuilder(applicationContext, dogdatabase::class.java, "my-database-$identifier")
            .fallbackToDestructiveMigration()
            .build()
    }

    private fun recreateDatabase(newIdentifier: String) {
        db.close()
        currentUserIdentifier = newIdentifier
        db = createDatabase(currentUserIdentifier)
    }

    // 사용자 로그인 이벤트 발생 시 호출되는 함수
    fun onUserLogin(identifier: String?) {
        if (currentUserIdentifier != identifier) {
            if (identifier != null) {
                recreateDatabase(identifier)
            }
        }
    }
}