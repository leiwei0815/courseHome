package com.example.coursehome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.coursehome.databinding.ItemLoadmoreBinding

class FooterAdapter(val adapter: CourseBrandAdapter,val context: Context):LoadStateAdapter<NetWorkStateItemViewHolder>() {
    override fun onBindViewHolder(holder: NetWorkStateItemViewHolder, loadState: LoadState) {
        holder.bindData(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): NetWorkStateItemViewHolder {
        val binding = ItemLoadmoreBinding.inflate(LayoutInflater.from(context),parent,false)
        return NetWorkStateItemViewHolder(binding){
            adapter.retry()
        }
    }
}