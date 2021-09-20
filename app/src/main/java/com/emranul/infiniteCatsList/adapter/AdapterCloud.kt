package com.emranul.infiniteCatsList.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.emranul.infiniteCatsList.R
import com.emranul.infiniteCatsList.data.response.CatResponseItem
import com.emranul.infiniteCatsList.databinding.CloudItemBinding
import javax.inject.Inject

//ekhane inject kora hoyeche jate AdapterCloud class ke jekono jayga theke access korte pari
class AdapterCloud @Inject constructor() :
    PagingDataAdapter<CatResponseItem, AdapterCloud.CloudViewHolder>(CloudDiffer) {

    inner class CloudViewHolder(private val binding: CloudItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: CatResponseItem) {
            binding.image.load(cat.url) {
                placeholder(R.drawable.ic_round_cloud_24)
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

    object CloudDiffer : DiffUtil.ItemCallback<CatResponseItem>() {
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

    override fun onBindViewHolder(holder: CloudViewHolder, position: Int) {
        val cat = getItem(position)
        cat?.let {
            holder.bind(cat)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CloudViewHolder {
        val binding = CloudItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CloudViewHolder(binding)
    }


}