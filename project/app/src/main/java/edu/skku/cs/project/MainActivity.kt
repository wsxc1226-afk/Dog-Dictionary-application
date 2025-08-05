package edu.skku.cs.project

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView

import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    companion object{
        const val nick ="nick"
        const val im ="image"
        const val gen ="gender"
        const val dname ="dname"
        const val dweight =""
        const val dheight =""
        const val bg =""
        const val ls =""
        const val tp =""
        const val imgurl =""
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val keyHash = Utility.getKeyHash(this)
        //Log.d("Hash", keyHash)

        supportActionBar?.setDisplayShowTitleEnabled(false)


        val myApp = application as myapp
        var db = myApp.db
        /*CoroutineScope(Dispatchers.IO).launch {
            db.dogDao().deleteAll()
        }*/


        val i=findViewById<Button>(R.id.login)
        val o=findViewById<Button>(R.id.logout)
        val img =findViewById<ImageView>(R.id.imageView)
        var TAG ="TAG"
        val ani: ValueAnimator =ObjectAnimator.ofFloat(img,"rotation",100f,0f,360f)
        ani.duration = 5000
        ani.start()
        val intent1 = Intent(this@MainActivity, Introduceactivity ::class.java)
        i.setOnClickListener {
            // 로그인 조합 예제
   // 참고 자료    https://developers.kakao.com/docs/latest/ko/kakaologin/android
// 카카오계정으로 로그인 공통 callback 구성
// 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
            val context = this
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오계정으로 로그인 실패", error)
                } else if (token != null) {
                    Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                            // 에러 처리
                        } else if (user != null) {
                            // 사용자 정보 가져오기 성공
                            val userId = user.id
                            val nickname = user.kakaoAccount?.profile?.nickname
                            val profileImage = user.kakaoAccount?.profile?.profileImageUrl
                            val gender = user.kakaoAccount?.gender
                            CoroutineScope(Dispatchers.IO).launch {
                                myApp.currentUserIdentifier=user.id.toString()
                                db=myApp.createDatabase(userId.toString())
                                myApp.onUserLogin(userId.toString())
                            }
                            intent1.putExtra(nick,nickname)
                            intent1.putExtra(im,profileImage)
                            intent1.putExtra(gen,gender)
                            startActivity(intent1)

                        }
                    }

                }
            }

// 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)

                    } else if (token != null) {
                        Log.i(TAG, "카카오톡 정보 가져오기 실패",error)
                        UserApiClient.instance.me { user, error ->
                            if (error != null) {
                                Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                                // 에러 처리
                            } else if (user != null) {
                                // 사용자 정보 가져오기 성공
                                val userId = user.id
                                val nickname = user.kakaoAccount?.profile?.nickname
                                val profileImage = user.kakaoAccount?.profile?.profileImageUrl
                                val gender = user.kakaoAccount?.gender
                                CoroutineScope(Dispatchers.IO).launch {
                                    myApp.currentUserIdentifier=user.id.toString()
                                    db=myApp.createDatabase(userId.toString())
                                    myApp.onUserLogin(userId.toString())
                                }
                                intent1.putExtra(nick,nickname)
                                intent1.putExtra(im,profileImage)
                                intent1.putExtra(gen,gender)
                                startActivity(intent1)
                            }
                        }
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
        }
        o.setOnClickListener {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Log.e(TAG, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                }
               /* else {
                    CoroutineScope(Dispatchers.IO).launch {
                        db.dogDao().deleteAll()
                    }
                    Log.i(TAG, "로그아웃 성공. SDK에서 토큰 삭제됨")
                }*/
            }
        }
    }
}