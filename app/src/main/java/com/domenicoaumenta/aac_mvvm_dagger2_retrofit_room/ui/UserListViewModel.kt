package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.api.UserApi
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.UserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

/**
 * Created by domenicoaumenta on 2020-01-09.
 */
class UserListViewModel @Inject constructor(private val userApi: UserApi) : ViewModel(){

    val userResultList : MutableLiveData<List<User>> = MutableLiveData()
    private var disposable: CompositeDisposable? = null

    private val repoLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()

    fun geUserList(): LiveData<List<User>> {
        return userResultList
    }

    fun getError(): LiveData<Boolean> {
        return repoLoadError
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    init {
        disposable = CompositeDisposable()
        showUserListFromNetwork()
    }

    private fun showUserListFromNetwork() {
        disposable?.add(userApi
            .getUsersByReputation(100)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<UserResponse>(){
                override fun onSuccess(t: UserResponse) {
                    repoLoadError.value = false
                    userResultList.value = t.users
                    loading.value = false
                }

                override fun onError(e: Throwable) {
                    repoLoadError.value = true
                    loading.value = false
                }
            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}