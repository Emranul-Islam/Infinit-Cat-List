package com.emranul.infiniteCatsList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.emranul.infiniteCatsList.R
import com.emranul.infiniteCatsList.data.response.CatResponseItem
import com.emranul.infiniteCatsList.databinding.LocalItemBinding
import javax.inject.Inject

//ekhane inject kora hoyeche jate AdapterCloud class ke jekono jayga theke access korte pari
class AdapterLocal @Inject constructor() :
    PagingDataAdapter<CatResponseItem, AdapterLocal.LocalViewHolder>(LocalDiffer) {

    inner class LocalViewHolder(private val binding: LocalItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: CatResponseItem) {
            binding.image.load(cat.url) {
                placeholder(R.drawable.ic_round_save_24)
            }
            binding.button.setOnClickListener {
                //niche je lemda fun likhechi setate ekhane object pass korechi
                listener?.let { it(cat) }
            }
        }
    }

    /**
     * eita lamda fun er maddhome object pass korechi normally ei kaj ta interface er
     * maddome kore thaki kinto ekhon evabei korte pari thats becouse power of Kotlin
     **/
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