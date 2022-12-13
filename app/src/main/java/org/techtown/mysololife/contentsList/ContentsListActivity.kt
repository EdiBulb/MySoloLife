package org.techtown.mysololife.contentsList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.util.ObjectsCompat.toString
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.techtown.mysololife.R
import org.techtown.mysololife.utils.FBRef
import java.util.ArrayList
import java.util.Arrays.toString
import java.util.Objects.toString

class ContentsListActivity : AppCompatActivity() {

    lateinit var myRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_list)

        //데이터 모델 타입을 넣어준다.
        val items = ArrayList<ContentModel>()
        val itemKeyList = ArrayList<String>()

        //어댑터
        val rvAdapter = ContentRVAdapter(baseContext,items, itemKeyList)

        //파이어베이스 데이터베이스에 쓰기
        val database = Firebase.database
        
        val category = intent.getStringExtra("category")

        if(category == "category1"){
            //카테고리가 1일 때
            myRef = database.getReference("contents")//path에 setValue로 데이터를 넣는다.

        } else if(category == "category2"){
            //카테고리 2
            myRef = database.getReference("contents2")//path에 setValue로 데이터를 넣는다.

        }
        //데이터 입력 코드
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //데이터 스냅샷에 뭐가 들어있는지 찍어보기 -> 데이터가 덩어리쨰로 들어있는 것을 확인했다.
                Log.d("ContentsListActivity",dataSnapshot.toString());

                //따라서 반복문을 써서 데이터를 하나씩 빼오겠다.
                for(dataModel in dataSnapshot.children){
                    Log.d("ContentsListActivity", dataModel.toString()) // 한 덩이씩 나오는 걸 확인할 수 있다.
                    Log.d("ContentListActivity", dataModel.key.toString())
                    //데이터의 value값을 item에 넣는다.
                    val item = dataModel.getValue(ContentModel::class.java)
                    //item을 items에 추가한다.
                    items.add(item!!)
                    itemKeyList.add(dataModel.key.toString())
                }
                //동기화 문제 때문에, 데이터를 받아오고 난 뒤, 어댑터를 리프레쉬 해야한다.
                rvAdapter.notifyDataSetChanged()

                Log.d("ContentsListActivity", "병훈"+items.toString()) //확인
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)


        //리사이클러 뷰 연결하기
        val rv : RecyclerView = findViewById(R.id.rv)


        rv.adapter = rvAdapter

        //두줄로 만드는 법 - GridLayoutManager를 사용해서 2를 집어넣기
        rv.layoutManager = GridLayoutManager(this,2)

        getBookmarkData()
    }

    private fun getBookmarkData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for(dataModel in dataSnapshot.children){
                    Log.d("getBookmarkData", dataModel.key.toString())
                    Log.d("getBookmarkData", dataModel.toString())
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.addValueEventListener(postListener)

    }
}