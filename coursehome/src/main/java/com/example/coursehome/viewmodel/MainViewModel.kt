package com.example.coursehome.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.coursehome.model.CourseBrandItemModel
import com.example.coursehome.repository.Repository

class MainViewModel @ViewModelInject constructor(private val courseBrandRepository: Repository) : ViewModel() {

    val data: LiveData<PagingData<CourseBrandItemModel>> =
        courseBrandRepository.fetchcourseBrandList().cachedIn(viewModelScope).asLiveData()

}