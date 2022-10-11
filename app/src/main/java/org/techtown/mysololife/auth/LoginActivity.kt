package org.techtown.mysololife.auth

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
import org.techtown.mysololife.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    //파이어베이스 인증 인스턴스 선언
    private lateinit var auth: FirebaseAuth

    //데이터바인딩
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        // 파이어베이스 인증 초기화
        auth = Firebase.auth

        //데이터 바인딩
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        //로그인 버튼 클릭 시
        binding.loginBtn.setOnClickListener {
            val email = binding.emailArea.text.toString()//이메일 가져오기
            val password = binding.passwordArea.text.toString()//비밀번호 가져오기


            //기존 사용자 로그인 시키기(파이어베이스 코드)
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {//성공하면
                        //회원가입 시키고, MainActivity로 이동시킨다.
                        val intent = Intent(this, MainActivity::class.java)

                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK  // 기존 액티비티를 날려버린다.(왜냐면, 회원가입 하고 MainActivity로 화면 전환후 뒤로가기 못하도록

                        startActivity(intent)
                        Toast.makeText(this, "로그인 성공", Toast.LENGTH_LONG).show()
                    } else {//실패하면
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}