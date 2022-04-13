package com.example.coursehome.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.coursehome.R

@BindingAdapter("bindingAvatar")
fun bindingAvatar(imageView: ImageView, url:String?){
    if (url?.isNotEmpty() == true){
        imageView.load(url){
            crossfade(true)   //淡入淡出
            placeholder(R.mipmap.ic_launcher_round) //展位图
        }
    }else{
        imageView.load(R.mipmap.ic_launcher_round)
    }
}