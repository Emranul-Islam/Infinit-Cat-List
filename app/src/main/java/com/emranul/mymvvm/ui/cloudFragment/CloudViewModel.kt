package com.emranul.mymvvm.ui.cloudFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.emranul.mymvvm.data.repositories.CatRepositories
import com.emranul.mymvvm.data.response.CatResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CloudViewModel @Inject constructor(
    private val repositories: CatRepositories
) : ViewModel() {

    val catsList = repositories.getCatsFromCloud().cachedIn(viewModelScope)

    val localCatsList = repositories.getLocalCats().cachedIn(viewModelScope)

    fun saveCate(catResponseItem: CatResponseItem) = viewModelScope.launch(Dispatchers.IO) {
        repositories.saveCat(catResponseItem)
    }

    fun delete(catResponseItem: CatResponseItem) = viewModelScope.launch(Dispatchers.IO) {
        repositories.deleteCat(catResponseItem)
    }

}