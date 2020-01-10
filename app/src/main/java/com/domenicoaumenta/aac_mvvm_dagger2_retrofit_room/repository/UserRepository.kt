package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.repository

import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.api.UserApi
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.UserResponse
import io.reactivex.Single
import javax.inject.Inject


/**
 * Created by domenicoaumenta on 2020-01-10.
 */
//TODO remove open to be replace with @OpenForTesting
open class UserRepository @Inject constructor(private val userApi: UserApi){

    fun getUsers() : Single<UserResponse> {
        return userApi.getUsersByReputation(100)
    }

}