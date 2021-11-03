package com.payback.pixabayapplication.presentation.view.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.payback.domain.model.Hits
import com.payback.pixabayapplication.R
import com.payback.pixabayapplication.databinding.FragmentDetailBinding
import eightbitlab.com.blurview.RenderScriptBlur

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    var decorView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        decorView = requireActivity().window.decorView
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail, container, false
        )
        //binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onStart() {
        super.onStart()
        val hitData = arguments?.getParcelable<Hits>("detail_hit")

        setUpViews(hitData)
    }


    fun setUpViews(hit: Hits?){
        val radius = 20f
        val windowBackground: Drawable = decorView!!.background
        binding.blurView.setupWith(decorView!!.findViewById(android.R.id.content))
            .setFrameClearDrawable(windowBackground)
            .setBlurAlgorithm(RenderScriptBlur(requireContext()))
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
            .setHasFixedTransformationMatrix(false)


        if (hit != null) {
            binding.detailComment.text = "Comments: ${hit.comments.toString()}"
            binding.detailDownload.text = "Downloads: ${hit.downloads.toString()}"
            binding.detailImageTag.text = "Image Tag: ${hit.tags}"
            binding.detailLike.text = "Likes: ${hit.like.toString()}"
            binding.detailUser.text = "Username: ${hit.getCapitalizeUser()}"


            Glide.with(this)
                .asBitmap()
                .load(hit.imageLarge)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : CustomTarget<Bitmap?>() {

                    override fun onLoadCleared(placeholder: Drawable?) {}
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap?>?
                    ) {
                        val bitmapDrawable = BitmapDrawable(requireContext().resources, resource)
                        binding.root.background = bitmapDrawable
                    }
                })

        }

    }

}