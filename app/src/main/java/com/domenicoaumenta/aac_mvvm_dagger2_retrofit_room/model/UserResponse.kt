package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model

import com.google.gson.annotations.SerializedName


/**
 * Created by domenicoaumenta on 2020-01-09.
 */
data class UserResponse (
    @SerializedName("items")
    var users: List<User>? = null,
    var hasMore: Boolean? = null,
    var quotaMax: Int? = null,
    var quotaRemaining: Int? = null)
