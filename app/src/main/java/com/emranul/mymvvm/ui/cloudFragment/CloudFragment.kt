package com.emranul.mymvvm.ui.cloudFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.emranul.mymvvm.adapter.AdapterCloud
import com.emranul.mymvvm.adapter.AdapterLoadState
import com.emranul.mymvvm.databinding.FragmentCloudBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CloudFragment : Fragment() {


    private val viewModel: CloudViewModel by viewModels()
    private var _binding: FragmentCloudBinding? = null
    private val binding get() = _binding!!
    private val TAG = "CloudFragment"

    @Inject
    lateinit var adapterCloud: AdapterCloud


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCloudBinding.inflate(layoutInflater)

        initAdapter()
        setUpRecycler()


        Log.d(TAG, "onCreateView: ")
        lifecycleScope.launch {
            viewModel.catsList.collectLatest {
                adapterCloud.submitData(it)
            }

        }


        return binding.root
    }

    private fun initAdapter() {
        adapterCloud.addLoadStateListener { loadStates ->
            binding.recyclerView.isVisible = loadStates.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadStates.source.refresh is LoadState.Loading
            binding.retryBtn.isVisible = loadStates.source.refresh is LoadState.Error
            binding.errorText.isVisible = loadStates.source.refresh is LoadState.Error

            val errorState = loadStates.source.append as? LoadState.Error
                ?: loadStates.source.prepend as? LoadState.Error
            errorState?.let {
                binding.errorText.text = it.error.toString()
                Toast.makeText(context, "error: ${it.error}", Toast.LENGTH_SHORT).show()
            }
        }


        binding.retryBtn.setOnClickListener {
            adapterCloud.retry()
        }

        adapterCloud.onClick {
            Snackbar.make(requireView(), "Cat Saved", Snackbar.LENGTH_LONG).show()
            Log.d(TAG, "setUpRecycler: $it")
            lifecycleScope.launch {
                viewModel.saveCate(it)
            }
        }
    }

    private fun setUpRecycler() {


        binding.recyclerView.apply {
            adapter = adapterCloud.withLoadStateHeaderAndFooter(
                AdapterLoadState { adapterCloud::retry },
                AdapterLoadState { adapterCloud::retry }
            )
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}