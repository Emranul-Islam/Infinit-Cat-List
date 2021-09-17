package com.emranul.mymvvm.ui.cloudFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emranul.mymvvm.data.repositories.CatRepositories
import com.emranul.mymvvm.data.resource.CatsResource
import com.emranul.mymvvm.data.response.CatResponse
import com.emranul.mymvvm.data.response.CatResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CloudViewModel @Inject constructor(
    private val repositories: CatRepositories
):ViewModel(){

    val catsList :MutableLiveData<CatsResource<CatResponse>> = MutableLiveData()

    fun getCatsFromServer(limit:Int) = viewModelScope.launch(Dispatchers.IO) {
        val response = repositories.getCatsFromCloud(limit)
        val data = response.body()
        if (response.isSuccessful && data != null) {
            catsList.postValue(CatsResource.Success(data))
        }else{
            catsList.postValue(CatsResource.Error(response.message() ?:"Error"))
        }
    }

    fun getLocalCats() = repositories.getLocalCats()

    fun saveCate(catResponseItem: CatResponseItem) = viewModelScope.launch(Dispatchers.IO) {
        repositories.saveCat(catResponseItem)
    }

    fun delete(catResponseItem: CatResponseItem) = viewModelScope.launch(Dispatchers.IO){
        repositories.deleteCat(catResponseItem)
    }

}