package com.example.musinsa.domain

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetListUseCase @Inject constructor(
    private val listApiRepository: ListApiRepository
){

    operator fun invoke(): Flow<List<Data>> = listApiRepository.getList()
}