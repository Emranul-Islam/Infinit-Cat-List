package com.emranul.mymvvm.ui.localFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.emranul.mymvvm.adapter.AdapterLocal
import com.emranul.mymvvm.databinding.FragmentLocalBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class LocalFragment : Fragment() {
    private var _binding: FragmentLocalBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapterLocal: AdapterLocal
    private val viewModel: LocalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLocalBinding.inflate(inflater, container, false)


        setUpRecycler()

        lifecycleScope.launchWhenCreated {
            viewModel.localCatsList.collect {
                adapterLocal.submitData(it)
            }

        }

        return binding.root
    }

    private fun setUpRecycler() {
        binding.recyclerView.apply {
            adapter = adapterLocal
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }

        adapterLocal.onClick {cat ->
            viewModel.delete(cat)
            Snackbar.make(requireView(),"Cat Deleted !",Snackbar.LENGTH_LONG).apply {
                setAction("Undo"){
                    viewModel.saveCate(cat)
                }
            }.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}