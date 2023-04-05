package com.example.musinsa.presentation

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.example.musinsa.BaseViewModel
import com.example.musinsa.data.ContentsDto
import com.example.musinsa.data.DataDto
import com.example.musinsa.domain.GetListUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

data class ListState(
    val contentModel: Async<List<DataDto>> = Uninitialized,

    val contentList: List<DataDto> = emptyList(),

    val scrollList: List<ContentsDto.GoodsDto> = emptyList(),

    val gridList: List<ContentsDto.GoodsDto> = emptyList(),

    val styleList: List<ContentsDto.StyleDto> = emptyList(),

    val gridItemCount: Int = 6,

    val styleItemCount: Int = 6,

    // 여기에 있는 State 를 통하여 Refresh, Refresh 할 아이템을 설정.
) : MavericksState

sealed interface UserAction {
    data class Refresh(val rootItem: ContentsDto) : UserAction

    data class More(val rootItem: ContentsDto) : UserAction
}

class ListViewModel @AssistedInject constructor(
    @Assisted state: ListState,
    getListUseCase: GetListUseCase,
) : BaseViewModel<UserAction, ListState>(state) {

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<ListViewModel, ListState> {
        override fun create(state: ListState): ListViewModel
    }

    companion object : MavericksViewModelFactory<ListViewModel, ListState> by hiltMavericksViewModelFactory()

    init {
        subscribeAction()
        getListUseCase().execute { copy(contentModel = it) }
    }

    private fun subscribeAction() {
        viewModelScope.launch {
            onAsync(
                ListState::contentModel,
                onFail = {
                    it.printStackTrace()
                },
                onSuccess = {
                    setState {
                        copy(
                            contentList = it,
                            scrollList = it.find { it.content.type == "SCROLL" }?.content?.goods ?: emptyList(),
                            gridList = it.find { it.content.type == "GRID" }?.content?.goods ?: emptyList(),
                            styleList = it.find { it.content.type == "STYLE" }?.content?.styles ?: emptyList()
                        )
                    }
                }
            )
        }
    }

    override fun onAction(action: UserAction) = withState {
        when (action) {
            is UserAction.Refresh -> {
                when (action.rootItem.type) {
                    "SCROLL" -> {
                        val item = action.rootItem.goods?.shuffled() ?: emptyList()
                        setState {
                            copy(
                                scrollList = item
                            )
                        }
                    }
                }
            }
            is UserAction.More -> {
                when (action.rootItem.type) {
                    "GRID" -> {
                        setState {
                            copy(
                                gridItemCount = it.gridItemCount + 3
                            )
                        }
                    }
                    "STYLE" -> {
                        setState {
                            copy(
                                styleItemCount = styleItemCount + 3
                            )
                        }
                    }
                }
            }
        }
    }
}