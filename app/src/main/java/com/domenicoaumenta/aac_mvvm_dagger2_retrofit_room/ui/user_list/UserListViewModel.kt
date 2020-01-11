package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.UserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable
import android.view.View
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.repository.UserRepository
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.EspressoIdlingResource

/**
 * Created by domenicoaumenta on 2020-01-09.
 */
class UserListViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){

    val userResultList : MutableLiveData<List<User>> = MutableLiveData()
    private var disposable: CompositeDisposable? = null

    private val repoLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Int>()

    fun geUserList(): LiveData<List<User>> {
        return userResultList
    }

    fun getError(): LiveData<Boolean> {
        return repoLoadError
    }

    fun getLoading(): LiveData<Int> {
        return loading
    }

    init {
        disposable = CompositeDisposable()
        loading.value = View.VISIBLE
    }

    fun showUserListFromNetwork() {

        EspressoIdlingResource.increment()

        disposable?.add(userRepository
            .getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<UserResponse>(){
                override fun onSuccess(t: UserResponse) {
                    repoLoadError.value = false
                    userResultList.value = t.users
                    loading.value = View.GONE
                }

                override fun onError(e: Throwable) {
                    repoLoadError.value = true
                    loading.value =  View.GONE
                }
            }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}