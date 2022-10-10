package org.techtown.mysololife

/*스플래쉬 화면입니다.*/

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.techtown.mysololife.auth.IntroActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //스플래쉬 끝나고 IntroActivity로 이동
        Handler().postDelayed({
            startActivity(Intent(this, org.techtown.mysololife.auth.IntroActivity::class.java))//IntroActivity로 이동
            finish()//스플래시 화면 종료
        },3000)//3초 뒤에 이동
    }
}