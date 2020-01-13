package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.repository

import android.view.View
import androidx.lifecycle.LiveData
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.api.UserApi
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.db.UserDatabase
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.UserResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.operators.single.SingleInternalHelper.toObservable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by domenicoaumenta on 2020-01-10.
 */
//TODO remove open to be replace with @OpenForTesting
open class UserRepository @Inject constructor(
    private val userApi: UserApi,
    private val database: UserDatabase
) {

    var disposable : Disposable? = null
    var dbDisposable : Disposable? = null
//    open fun getUsers(onError: (Throwable) -> Unit = {}): Observable<List<User>> {
//        clear()
//        val data = database.userDao().loadUsers()
//
//       disposable = userApi.getUsersByReputation(100)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(object : DisposableSingleObserver<UserResponse>() {
//                override fun onSuccess(t: UserResponse) {
//                    t.users?.let {
//                        database.runInTransaction{
//                            database.userDao().insert(it)
//                        }
//                    }
//                }
//
//                override fun onError(e: Throwable) {
//                  onError.invoke(e)
//                }
//            })
//        return data
//    }

    fun getUsers(onError: (Throwable) -> Unit = {}): Observable<List<User>> {
        return Observable.concatArray(
            getUsersFromDb(),
            getUsersFromApi(onError))
    }


    fun getUsersFromDb(): Observable<List<User>> {

        return database.userDao().loadUsers()
            .doOnNext {
                println("Dispatching ${it.size} users from DB...")
            }
    }

    fun getUsersFromApi(onError: (Throwable) -> Unit = {}): Observable<List<User>> {

        var users : Observable<List<User>> = Observable.just(emptyList())

        disposable = userApi.getUsersByReputation(100)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<UserResponse>() {
                override fun onSuccess(t: UserResponse) {
                    t.users?.let {
                       users = Observable.just(it)
                       storeUsersInDb(it)
                    }
                }

                override fun onError(e: Throwable) {
                    onError.invoke(e)
                }
            })
       return users
    }

    fun storeUsersInDb(users: List<User>) {
        dbDisposable = Observable.fromCallable { database.userDao().insert(users) }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                println("Inserted ${users.size} users from API in DB...")
            }
    }


    fun clear(){
        disposable?.dispose()
        dbDisposable?.dispose()
    }
}