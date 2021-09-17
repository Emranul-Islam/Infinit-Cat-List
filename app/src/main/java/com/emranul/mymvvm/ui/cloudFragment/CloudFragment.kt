package com.emranul.mymvvm.ui.cloudFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.emranul.mymvvm.data.resource.CatsResource
import com.emranul.mymvvm.databinding.FragmentCloudBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CloudFragment : Fragment() {


    private val viewModel:CloudViewModel by viewModels()
    private var _binding:FragmentCloudBinding? = null
    private val binding get()=_binding!!
    private val TAG = "CloudFragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCloudBinding.inflate(layoutInflater)


        viewModel.getCatsFromServer(50)
        viewModel.catsList.observe(viewLifecycleOwner,{response ->
            when(response){
                is CatsResource.Success -> {
                    response.data.let {
                        Log.d(TAG, "onCreateView: Success $it")
                    }
                }
                is CatsResource.Error ->{
                    response.error.let {
                        Log.d(TAG, "onCreateView: error $it")
                    }
                }
                is CatsResource.Loading ->{
                    Log.d(TAG, "onCreateView: Loading")
                }
                else -> Unit
            }
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}