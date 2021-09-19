package com.emranul.mymvvm.data.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.emranul.mymvvm.R
import com.emranul.mymvvm.data.response.CatResponseItem
import com.emranul.mymvvm.databinding.CloudItemBinding
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject


class AdapterCloud @Inject constructor() : PagingDataAdapter<CatResponseItem,AdapterCloud.CloudViewHolder>(CloudDiffer) {
    class CloudViewHolder(val binding:CloudItemBinding):RecyclerView.ViewHolder(binding.root)

    object CloudDiffer:DiffUtil.ItemCallback<CatResponseItem>(){
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
            holder.binding.image.load(cat.url){
                placeholder(R.drawable.ic_round_cloud_24)
            }
        }

        holder.binding.button.setOnClickListener {
            listener?.let { it(cat!!) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CloudViewHolder {
        val binding = CloudItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CloudViewHolder(binding)
    }

    private var listener:((CatResponseItem)-> Unit)? = null

    fun onClick(click:(CatResponseItem) -> Unit){
        listener = click
    }
}