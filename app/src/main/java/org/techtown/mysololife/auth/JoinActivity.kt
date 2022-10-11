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
import org.techtown.mysololife.databinding.ActivityJoinBinding

class JoinActivity : AppCompatActivity() {

    //파이어베이스 인증 인스턴스 선언
    private lateinit var auth: FirebaseAuth

    //데이터 바인딩
    private lateinit var binding: ActivityJoinBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        setContentView(R.layout.activity_join)

        // 파이어베이스 인증 초기화
        auth = Firebase.auth


        //데이터 바인딩
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)

        //회원가입 버튼 클릭 시 - 입려된 값을 가져와서 회원가입을 시킨다.
        binding.joinBtn.setOnClickListener {

            var isGoToJoin = true; // 회원가입을 위한 모든 조건을 만족하는지 판단하기 위한 flag 변수

            val email = binding.emailArea.text.toString()//이메일 가져오기
            val password1 = binding.passwordArea1.text.toString() // 비밀번호 가져오기
            val password2 = binding.passwordArea2.text.toString() //비밀번호 체크 가져오기

            //이메일 값이 비어있는지 확인
            if (email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false // 비어있으니, false로 변경
            }

            //패스워드1 값이 비어있는지 확인
            if (password1.isEmpty()) {
                Toast.makeText(this, "password1을 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false // 비어있으니, false로 변경

            }

            //패스워드2 값이 비어있는지 확인
            if (password2.isEmpty()) {
                Toast.makeText(this, "password2을 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false // 비어있으니, false로 변경

            }

            //패스워드 1과 2가 같은지 확인하기
            if (!password1.equals(password2)) { // 똑같지 않다면
                Toast.makeText(this, "비밀번호를 똑같이 입력해주세요", Toast.LENGTH_LONG).show()
                isGoToJoin = false // 같지 않으니, false로 변경

            }
            //패스워드가 6자리 이상인지 확인하기
            if (password1.length < 6) { //
                Toast.makeText(this, "비밀번호를 6자리 이상으로 입력해주세요.", Toast.LENGTH_LONG).show()
                isGoToJoin = false // 비밀번호가 부족하니, false로 변경
            }

            // 위의 조건들을 다 만족하면, 회원가입을 시켜준다.
            if (isGoToJoin) {
                //신규 사용자 가입
                //조건을 만족한, email이랑 password1를 받아서 가입시킨다.
                auth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {//성공하는 경우
                        Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()

                        //회원가입 시키고, MainActivity로 이동시킨다.
                        val intent = Intent(this,MainActivity::class.java)

                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK  // 기존 액티비티를 날려버린다.(왜냐면, 회원가입 하고 MainActivity로 화면 전환후 뒤로가기 못하도록

                        startActivity(intent)

                    } else {//실패하는 경우
                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}