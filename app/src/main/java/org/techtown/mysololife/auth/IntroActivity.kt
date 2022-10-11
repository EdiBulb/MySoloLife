package org.techtown.mysololife.auth
/*스플래쉬 화면 다음에 뜨는 인트로 화면입니다.*/

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.techtown.mysololife.MainActivity
import org.techtown.mysololife.R
import org.techtown.mysololife.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    //파이어베이스 인증 인스턴스 선언
    private lateinit var auth: FirebaseAuth

    //데이터 바인딩
    //데이터 바인딩이 뭐지?
    private lateinit var binding : ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_intro) // 데이터 바인딩을 해서 없어도 된다.

        //파이어베이스 인증 초기화
        auth = Firebase.auth

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

        //비회원 로그인 클릭 시
        binding.noAccountBtn.setOnClickListener {
            //비회원 인증하는 파이어베이스 코드
            auth.signInAnonymously()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {//성공하면
                        Toast.makeText(this,"로그인 성공",Toast.LENGTH_LONG).show()

                        //메인화면으로 전환
                        val intent = Intent(this,MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    } else {//실패하면

                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}