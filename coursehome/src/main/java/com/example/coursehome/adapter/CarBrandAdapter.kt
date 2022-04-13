package com.example.coursehome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.coursehome.databinding.ItemCoursebrandBinding
import com.example.coursehome.model.CourseBrandItemModel

class CourseBrandAdapter(private val context: Context) :
    PagingDataAdapter<CourseBrandItemModel, BindingViewHolder>(object :
        DiffUtil.ItemCallback<CourseBrandItemModel>() {
        override fun areItemsTheSame(
            oldItem: CourseBrandItemModel,
            newItem: CourseBrandItemModel
        ): Boolean {
            return oldItem.goodsName == newItem.goodsName
        }

        override fun areContentsTheSame(
            oldItem: CourseBrandItemModel,
            newItem: CourseBrandItemModel
        ): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        getItem(position).let { item ->
            val binding = holder.binding as ItemCoursebrandBinding
            binding.courseBrand = item

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemCoursebrandBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }
}