package com.example.musinsa.domain

import com.example.musinsa.data.DataDto
import kotlinx.coroutines.flow.Flow

interface ListApiRepository {

    fun getList(): Flow<List<DataDto>>
}