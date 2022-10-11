package org.techtown.mysololife.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import org.techtown.mysololife.R
import org.techtown.mysololife.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    /*데이터 바인딩*/
    private lateinit var binding : FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        Log.d("HomeFragment","onCreateView")// 로그찍어보기

        //데이터바인딩 - 프래그먼트를 바인딩 할 때는 좀 다르다.
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container,false)

        //tipTap을 클릭했을 떄
        binding.tipTap.setOnClickListener{
//            Log.e("HomeFragment","tipTap")//로그 찍어보기 - 로그 찍는 거 배우기(완료)

            //홈 프래그먼트에서 tip 프레그먼트로로
           it.findNavController().navigate(R.id.action_homeFragment_to_tipFragment)
        }

        //talk 탭을 클릭했을 떄
        binding.talkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_talkFragment)
        }

        //bookmark 탭을 클릭했을 떄
        binding.bookmarkTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_bookmarkFragment)
        }

        //store 탭을 클릭했을 떄
        binding.storeTap.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_storeFragment)
        }

        return binding.root
    }


}