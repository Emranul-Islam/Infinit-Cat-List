package com.emranul.infiniteCatsList.ui.cloudFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.emranul.infiniteCatsList.data.repositories.CatRepositories
import com.emranul.infiniteCatsList.data.response.CatResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CloudViewModel @Inject constructor(
    private val repositories: CatRepositories
) : ViewModel() {

    val catsList = repositories.getCatsFromCloud().cachedIn(viewModelScope)

    fun saveCate(catResponseItem: CatResponseItem) = viewModelScope.launch(Dispatchers.IO) {
        repositories.saveCat(catResponseItem)
    }

}