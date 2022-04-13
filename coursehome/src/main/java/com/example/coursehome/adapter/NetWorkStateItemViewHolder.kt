package com.example.coursehome.adapter

import android.view.View
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.coursehome.databinding.ItemLoadmoreBinding

class NetWorkStateItemViewHolder(
    private val binding: ItemLoadmoreBinding,
    val retryCallBack: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: LoadState) {
        binding.apply {
            //正在加载
            progress.isVisible = data is LoadState.Loading

            //加载失败, 显示重试
            tvAgain.isVisible = data is LoadState.Error
            tvAgain.setOnClickListener { retryCallBack() }

            //加载失败显示原因
            tvLoading.isVisible = !(data as? LoadState.Error)?.error?.message.isNullOrBlank()
            tvLoading.text = (data as? LoadState.Error)?.error?.message
        }
    }
}

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        if (value) View.VISIBLE else View.GONE
    }