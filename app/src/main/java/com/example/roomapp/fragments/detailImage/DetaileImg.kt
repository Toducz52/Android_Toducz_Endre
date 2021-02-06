package com.example.roomapp.fragments.detailImage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.roomapp.MainActivity
import com.example.roomapp.R
import com.example.roomapp.model.UploadImg
import com.example.roomapp.viewmodel.LocalDataViewModel
import kotlinx.android.synthetic.main.fragment_detail_img.view.*


class DetaileImg : Fragment() {

    private lateinit var mLocalDataViewModel: LocalDataViewModel
    private var urlImgs: MutableList<UploadImg> = mutableListOf<UploadImg>()
    private var imgIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val args by navArgs<DetaileImgArgs>()
    lateinit var view_: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view_ = inflater.inflate(R.layout.fragment_detail_img, container, false)

        mLocalDataViewModel = ViewModelProvider(this).get(LocalDataViewModel::class.java)
        mLocalDataViewModel.readUrlImg.observe(viewLifecycleOwner, Observer { dataUrlImgs ->
            urlImgs.clear()
            urlImgs.add(UploadImg(MainActivity.loggedUser?.email!!, args.restaurant.image_url!!, args.restaurant?.id.toString()))
            urlImgs.addAll(dataUrlImgs)
            Log.d("Detail Img", "Interesting")
            imgIndex = urlImgs.size - 1
            loadImg(urlImgs.get(imgIndex).url)
        })


        urlImgs.add(UploadImg(MainActivity.loggedUser?.email!!, args?.restaurant.image_url!!, args.restaurant?.id.toString()))
        mLocalDataViewModel.readlocalUrl(MainActivity.loggedUser?.email!!, args.restaurant?.id!!)
        if(URLUtil.isValidUrl(urlImgs.get(0).url)){
            loadImg(urlImgs.get(0).url)
        }


      view_.load_btn.setOnClickListener {
           val intent = Intent()
            intent.type = "imgage/*"
         intent.action = Intent.ACTION_GET_CONTENT
           startActivityForResult(
              Intent.createChooser(intent, "Pick an image"),
                1000
            )
       }

        view_.next_btn.setOnClickListener {

            imgIndex++

            if(imgIndex == urlImgs.size){
                imgIndex = 0
            }

            loadImg(urlImgs.get(imgIndex).url)
        }

        view_.previouse_btn.setOnClickListener {

            imgIndex--

            if(imgIndex < 0){
                imgIndex = urlImgs.size - 1
            }

            loadImg(urlImgs.get(imgIndex).url)
        }

        return view_
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1000){

            var imgUrl = data?.data.toString()

            mLocalDataViewModel.addUrlImg(UploadImg(MainActivity.loggedUser?.email!!, imgUrl, args.restaurant?.id.toString()))
            urlImgs.add(UploadImg( MainActivity.loggedUser?.email!!, imgUrl, args.restaurant?.id.toString()))
            imgIndex = urlImgs.size - 1
            loadImg(urlImgs.get(imgIndex).url)
        }
    }

    fun loadImg(url:String){
        Glide.with(this)
            .load(url)
            .placeholder(R.drawable.ic_baseline_fastfood_24)
            .apply(RequestOptions().override(view_.imageView2.width, view_.imageView2.height))
            .into(view_.imageView2)
    }

}