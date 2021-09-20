package com.emranul.mymvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.emranul.mymvvm.R
import com.emranul.mymvvm.data.response.CatResponseItem
import com.emranul.mymvvm.databinding.LocalItemBinding
import javax.inject.Inject

class AdapterLocal @Inject constructor() :
    PagingDataAdapter<CatResponseItem, AdapterLocal.LocalViewHolder>(LocalDiffer) {

    inner class LocalViewHolder(private val binding: LocalItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: CatResponseItem) {
            binding.image.load(cat.url) {
                placeholder(R.drawable.ic_round_save_24)
            }
            binding.button.setOnClickListener {
                listener?.let { it(cat) }
            }
        }
    }

    private var listener: ((CatResponseItem) -> Unit)? = null
    fun onClick(click: (CatResponseItem) -> Unit) {
        listener = click
    }

    object LocalDiffer : DiffUtil.ItemCallback<CatResponseItem>() {
        override fun areItemsTheSame(oldItem: CatResponseItem, newItem: CatResponseItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: CatResponseItem,
            newItem: CatResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        val cat = getItem(position)
        cat?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        val binding = LocalItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocalViewHolder(binding)
    }


}