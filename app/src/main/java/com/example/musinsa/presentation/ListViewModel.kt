package com.example.musinsa.presentation

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.example.musinsa.BaseViewModel
import com.example.musinsa.domain.Content
import com.example.musinsa.domain.Data
import com.example.musinsa.domain.GetListUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

data class ListState(
    val contentModel: Async<List<Data>> = Uninitialized,

    val contentList: List<Data> = emptyList(),

    // 여기에 있는 State 를 통하여 Refresh, Refresh 할 아이템을 설정.
) : MavericksState

sealed interface UserAction {

    object Loading: UserAction
    data class Refresh(val rootItem: Content) : UserAction

    data class More(val rootItem: Content) : UserAction
}

class ListViewModel @AssistedInject constructor(
    @Assisted state: ListState,
    private val getListUseCase: GetListUseCase,
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
                        )
                    }
                }
            )
        }
    }

    override fun onAction(action: UserAction) = withState {
        when (action) {
            UserAction.Loading -> {
                getListUseCase().execute { model ->
                    copy(contentModel = model)
                }
            }
            is UserAction.Refresh -> {
                val current = it.contentList.map { data ->
                    if (data.contents.type == action.rootItem.type) {
                        data.copy(
                            contents = data.contents.copy(
                                detail = data.contents.detail.shuffled()
                            )
                        )
                    } else {
                        data.copy()
                    }
                }
                setState {
                    copy(
                        contentList = current
                    )
                }
            }
            is UserAction.More -> {
                val current = it.contentList.map { data ->
                    if (data.contents.type == action.rootItem.type) {
                        data.copy(
                            contents = data.contents.copy(
                                contentSize = data.contents.contentSize + 3
                            )
                        )
                    } else {
                        data.copy()
                    }
                }
                setState {
                    copy(
                        contentList = current
                    )
                }
            }
        }
    }
}