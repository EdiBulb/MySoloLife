package org.techtown.mysololife

/*스플래쉬 화면입니다.*/

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.techtown.mysololife.auth.IntroActivity

class SplashActivity : AppCompatActivity() {

    //파이어베이스 인증 인스턴스 선언
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 파이어베이스 인증 초기화
        auth = Firebase.auth

        //로그인 된 상태인지 확인하기
        if(auth.currentUser?.uid == null){ //uid가 존재하지 않으면면
           Log.d("SplashActivity", "null")

            //로그인 x이므로 IntroActivity로 이동
            Handler().postDelayed({
                startActivity(Intent(this, org.techtown.mysololife.auth.IntroActivity::class.java))//IntroActivity로 이동
                finish()//스플래시 화면 종료
            },3000)//3초 뒤에 이동

        }else{
            Log.d("SplashActivity","not null")

            //로그인 된 상태이므로, 스플래쉬 끝나고 MainActivity 이동
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))//IntroActivity로 이동
                finish()//스플래시 화면 종료
            },3000)//3초 뒤에 이동
        }

//        //스플래쉬 끝나고 IntroActivity로 이동
//        Handler().postDelayed({
//            startActivity(Intent(this, org.techtown.mysololife.auth.IntroActivity::class.java))//IntroActivity로 이동
//            finish()//스플래시 화면 종료
//        },3000)//3초 뒤에 이동
    }
}