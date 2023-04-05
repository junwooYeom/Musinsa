package com.example.musinsa.data

import android.util.Log
import com.example.musinsa.domain.Data
import com.example.musinsa.domain.ListApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ListApiRepositoryImpl @Inject constructor(
    private val networkService: NetworkService,
) : ListApiRepository {

    override fun getList(): Flow<List<Data>> = flow {
        val response = networkService.getItemList().data.map {
            it.toData()
        }
        Log.d("MyTag", response.toString())
        emit(response)
    }
}