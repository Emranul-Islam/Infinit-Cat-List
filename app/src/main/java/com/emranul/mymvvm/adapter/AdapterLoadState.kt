package com.emranul.mymvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.emranul.mymvvm.databinding.LoadStateBinding

class AdapterLoadState constructor(private val adapterCloud: AdapterCloud) :
    LoadStateAdapter<AdapterLoadState.LoaderStateViewHolder>() {


    override fun onBindViewHolder(holder: LoaderStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoaderStateViewHolder {
        return LoaderStateViewHolder(
            LoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), adapterCloud
        )
    }

    class LoaderStateViewHolder(private val binding: LoadStateBinding, adapterCloud: AdapterCloud) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.button2.setOnClickListener {
                adapterCloud.retry()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                button2.isVisible = loadState !is LoadState.Loading
                imageView2.isVisible = loadState !is LoadState.Loading
                textView.isVisible = loadState !is LoadState.Loading
            }
        }
    }

}