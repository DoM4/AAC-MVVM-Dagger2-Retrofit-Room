package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.api

import androidx.lifecycle.LiveData
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.UserResponse
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by domenicoaumenta on 2020-01-09.
 */
interface UserApi {

    @GET("2.2/users")
    fun getUsersByReputation(
        @Query("pagesize") pageSize: Int = 1,
        @Query("order") order: String = "desc",
        @Query("sort") sort: String = "reputation",
        @Query("site") site: String = "stackoverflow"
    ): LiveData<ApiResponse<UserResponse>>

}