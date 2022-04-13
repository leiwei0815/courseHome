package com.example.coursehome.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.coursehome.adapter.CourseBrandAdapter
import com.example.coursehome.adapter.FooterAdapter
import com.example.coursehome.databinding.ActivityMainBinding
import com.example.coursehome.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mViewModel: MainViewModel by viewModels()

    private val mcourseBrandAdapter by lazy {
        CourseBrandAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.recyclerView.adapter =
            mcourseBrandAdapter.withLoadStateFooter(FooterAdapter(mcourseBrandAdapter, this))

        binding.swipeRefreshLayout.setOnRefreshListener {
            mcourseBrandAdapter.refresh()
        }
        lifecycleScope.launchWhenCreated {
            mcourseBrandAdapter.loadStateFlow.collectLatest { state ->
                binding.swipeRefreshLayout.isRefreshing = state.refresh is LoadState.Loading
            }
        }

        mViewModel.data.observe(this) {
//            binding.recyclerView.adapter
            mcourseBrandAdapter.submitData(lifecycle, it)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}