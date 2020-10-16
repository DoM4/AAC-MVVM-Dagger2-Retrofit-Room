package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.UserResponse
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.repository.UserRepository
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.ApiResponse
import io.reactivex.BackpressureStrategy
import javax.inject.Inject

/**
 * Created by domenicoaumenta on 2020-01-09.
 */
class UserListViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    var userList: LiveData<ApiResponse<UserResponse>> = userRepository.getLiveDataUsers()

    private val mutableOnError = MutableLiveData<Throwable?>()
    val onError: LiveData<Throwable?>
        get() = mutableOnError


    fun getUserById(userId: Int): LiveData<User> =
        LiveDataReactiveStreams.fromPublisher(
            userRepository.getUserById(userId).toFlowable(BackpressureStrategy.LATEST)
        )

//    fun showUserListFromNetwork() : LiveData<List<User>> {
//        EspressoIdlingResource.increment()
//        return LiveDataReactiveStreams.fromPublisher(userRepository.getUsers {mutableOnError.value = it}.toFlowable(BackpressureStrategy.LATEST))
//    }

    override fun onCleared() {
        super.onCleared()
        userRepository.clear()
    }
}