package com.example.musinsa.domain

import kotlinx.coroutines.flow.Flow

interface ListApiRepository {

    fun getList(): Flow<List<Data>>
}