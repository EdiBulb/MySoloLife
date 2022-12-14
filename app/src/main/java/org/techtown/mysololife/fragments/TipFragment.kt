package org.techtown.mysololife.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import org.techtown.mysololife.R
import org.techtown.mysololife.contentsList.ContentsListActivity
import org.techtown.mysololife.databinding.FragmentTipBinding



class TipFragment : Fragment() {

    private lateinit var binding: FragmentTipBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tip,container,false)

        //카테고리 1번이 눌렸을 경우
        binding.category1.setOnClickListener {
            Log.d("TipFragment","실행")
            val intent = Intent(context,org.techtown.mysololife.contentsList.ContentsListActivity::class.java)
            //인텐트로 카테고리 정보도 같이 넘기기
            intent.putExtra("category", "category1")
            startActivity(intent)
        }

        binding.category2.setOnClickListener {
            val intent = Intent(context, ContentsListActivity::class.java)
            intent.putExtra("category", "category2")
            startActivity(intent)
        }


        /*네비게이션 탭*/
        //홈이 클릭되는 경우
        binding.homeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_homeFragment)
        }
        //토크가 클릭되는 경우
        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_talkFragment)

        }
        //북마크가 클릭되는 경우
        binding.bookmarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_bookmarkFragment)

        }
        //스토어가 클릭되는 경우
        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_tipFragment_to_storeFragment)

        }


        return binding.root
    }


}