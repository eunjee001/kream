package com.kkyoungs.kream

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.kkyoungs.kream.databinding.FragmentRecommandBinding
import java.util.Timer
import java.util.TimerTask

class RecommendFragment : Fragment(){
    private var _binding : FragmentRecommandBinding ?= null
    private val binding get() = _binding!!
    private lateinit var images : Array<String>
    private lateinit var slidingImageDots : Array<ImageView?>
    private var slidingDotsCount = 0
    private var currentPage = 0

    private val slidingCallback = object :ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            for (i in 0 until slidingDotsCount){
                slidingImageDots[i]?.setImageDrawable(
                    ContextCompat.getDrawable(context!!, R.drawable.non_active_dot)
                )
            }
            slidingImageDots[position]?.setImageDrawable(
                ContextCompat.getDrawable(context!!, R.drawable.active_dot)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inflate는 xml이 표기된 레이아웃들을 메모리에 로딩된 후 객체화 시키는 과정.
        _binding = FragmentRecommandBinding.inflate(inflater, container, false)
        setUpSlidingViewPager()
        return binding.root
    }


    private fun setUpSlidingViewPager(){
        images = resources.getStringArray(R.array.image_urls_array)

        val landingImagesAdapter = SlidingImagesAdapter(requireActivity(), images.size)
        _binding!!.layoutMainMenuContainer.slidingViewPager.apply {
            adapter = landingImagesAdapter
            registerOnPageChangeCallback(slidingCallback)
        }
        slidingDotsCount = images.size
        slidingImageDots = arrayOfNulls(slidingDotsCount)

        for (i in 0 until slidingDotsCount){
            slidingImageDots[i] = ImageView(context)
            slidingImageDots[i]?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.non_active_dot))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(8,0,8,0)
            binding.layoutMainMenuContainer.sliderDot.addView(slidingImageDots[i], params)
        }
        slidingImageDots[0]?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.active_dot))
        val handler = Handler()
        val update = Runnable {
            if (currentPage == images.size){
                currentPage = 0
            }
            binding.layoutMainMenuContainer.slidingViewPager.setCurrentItem(currentPage++, true)
        }
        Timer().schedule(object : TimerTask(){
            override fun run() {
                handler.post(update)
            }

        }, 3500, 3500)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}