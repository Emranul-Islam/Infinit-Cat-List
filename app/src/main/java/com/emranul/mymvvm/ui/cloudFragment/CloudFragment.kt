package com.emranul.mymvvm.ui.cloudFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.emranul.mymvvm.data.adapter.AdapterCloud
import com.emranul.mymvvm.databinding.FragmentCloudBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
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

        setUpRecycler()

        Log.d(TAG, "onCreateView: ")
        lifecycleScope.launch {
            viewModel.catsList.collect {
                adapterCloud.submitData(it)
            }

        }


        return binding.root
    }

    private fun setUpRecycler() {
        binding.recyclerView.apply {
            adapter = adapterCloud
            layoutManager = LinearLayoutManager(context)
        }

        adapterCloud.onClick {
            Snackbar.make(requireView(), "Cat Saved !!", Snackbar.LENGTH_LONG).show()
            Log.d(TAG, "setUpRecycler: $it")
            lifecycleScope.launch {
                viewModel.saveCate(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}