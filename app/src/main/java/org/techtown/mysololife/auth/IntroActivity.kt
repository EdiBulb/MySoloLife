package org.techtown.mysololife.auth
/*스플래쉬 화면 다음에 뜨는 인트로 화면입니다.*/

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.techtown.mysololife.R
import org.techtown.mysololife.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    //데이터 바인딩
    //데이터 바인딩이 뭐지?
    private lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_intro) // 데이터 바인딩을 해서 없어도 된다.

        //데이터 바인딩
        binding = DataBindingUtil.setContentView(this,R.layout.activity_intro)

        //로그인 버튼 클릭 시
        binding.loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        //회원가입 버튼 클릭 시
        binding.joinBtn.setOnClickListener {
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }
    }
}