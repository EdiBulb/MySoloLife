package org.techtown.mysololife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import org.techtown.mysololife.R
import org.techtown.mysololife.contentsList.ContentModel
import org.techtown.mysololife.databinding.FragmentBookmarkBinding
import org.techtown.mysololife.databinding.FragmentTalkBinding
import org.techtown.mysololife.utils.FBAuth
import org.techtown.mysololife.utils.FBRef

class BookmarkFragment : Fragment() {
    private lateinit var binding : FragmentBookmarkBinding

    private val TAG = BookmarkFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bookmark,container,false)


        // 1. 전체 카테고리에 있는 컨텐츠 데이터들을 다 가져옴!
        getCategoryDate()

        // 2. 사용자가 북마크한 정보를 다 가져옴!
        getBookmarkData()

        // 3. 전체 컨텐츠 중에서, 사용자가 북마크한 정보만 보여줌!


        //홈이 클릭되는 경우
        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_homeFragment)
        }
        //팁이 클릭되는 경우
        binding.tipTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_tipFragment)

        }
        //토크탭이 클릭되는 경우
        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_talkFragment)

        }
        //스토어가 클릭되는 경우
        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_bookmarkFragment_to_storeFragment)

        }

        return binding.root
    }

    private fun getCategoryDate(){
        //데이터 입력 코드
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //데이터 스냅샷에 뭐가 들어있는지 찍어보기 -> 데이터가 덩어리쨰로 들어있는 것을 확인했다.
                Log.d("ContentsListActivity",dataSnapshot.toString());

                //따라서 반복문을 써서 데이터를 하나씩 빼오겠다.
                for(dataModel in dataSnapshot.children){
                    Log.d(TAG, dataModel.toString())
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.category1.addValueEventListener(postListener)
        FBRef.category2.addValueEventListener(postListener)
    }

    private fun getBookmarkData(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {


                for(dataModel in dataSnapshot.children){

                    Log.e(TAG, dataModel.toString())
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("ContentsListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        FBRef.bookmarkRef.child(FBAuth.getUid()).addValueEventListener(postListener)

    }

}