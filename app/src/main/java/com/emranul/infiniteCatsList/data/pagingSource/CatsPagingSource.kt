package com.emranul.infiniteCatsList.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.emranul.infiniteCatsList.data.api.ApiServices
import com.emranul.infiniteCatsList.data.response.CatResponseItem
import retrofit2.HttpException

class CatsPagingSource constructor(private val api: ApiServices) : PagingSource<Int, CatResponseItem>() {
    override fun getRefreshKey(state: PagingState<Int, CatResponseItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatResponseItem> {

        return try {
            val currentPage: Int = params.key ?: 1
            val response = api.getImages(currentPage, params.loadSize)
            val responseData = response.body() ?: emptyList()

            LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (responseData.isEmpty()) null else currentPage + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}