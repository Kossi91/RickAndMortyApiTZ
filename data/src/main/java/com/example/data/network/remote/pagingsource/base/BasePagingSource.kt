package com.example.data.network.remote.pagingsource.base

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.network.remote.dtos.RickyAndMortyResponseDto
import retrofit2.HttpException
import java.io.IOException

abstract class BasePagingSource<ValueDto : Any, Value : Any>(
    private val service: suspend (nextPage: Int) -> RickyAndMortyResponseDto<ValueDto>,
    private val mappedData: (data: List<ValueDto>) -> List<Value>
) : PagingSource<Int, Value>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        return try {
            val nextPage: Int = (params.key ?: 1)
            val response = service(nextPage)
            val next = response.info.next
            val nextPageNumber = if (next == null) {
                null
            } else {
                Uri.parse(response.info.next).getQueryParameter("page")!!.toInt()
            }
            LoadResult.Page(
                data = mappedData(response.results),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (http: HttpException) {
            LoadResult.Error(http)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey
        }
    }
}
