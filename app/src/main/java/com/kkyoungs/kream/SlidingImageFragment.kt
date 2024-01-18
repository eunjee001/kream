package com.kkyoungs.kream

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.kkyoungs.kream.databinding.ItemBannerBinding
import com.kkyoungs.kream.databinding.ItemSlidingBinding
class SlidingImageFragment : Fragment(R.layout.item_sliding) {
    private var _binding : ItemSlidingBinding?= null
    private val binding get() = _binding!!

    companion object{
        const val ARG_POSITION = "position"

        fun getInstance(position : Int):Fragment{
            val fragment = SlidingImageFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ItemSlidingBinding.inflate(inflater, container, false)
        val position = requireArguments().getInt(ARG_POSITION)
        val landingImagesArray = requireContext().resources.getStringArray(R.array.image_urls_array)
        val imageString = landingImagesArray[position]
//        val id = resources.getIdentifier(imageString, "drawable", requireActivity().packageName)
//        _binding!!.slidingImage.setImageResource(id)
        Glide.with(this)
            .load(imageString)
            .into(_binding!!.slidingImage);


        return binding.root
    }
}