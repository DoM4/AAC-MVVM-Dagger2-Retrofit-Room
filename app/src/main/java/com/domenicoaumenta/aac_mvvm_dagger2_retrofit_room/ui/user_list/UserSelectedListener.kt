package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list

import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User


/**
 * Created by domenicoaumenta on 2020-01-10.
 */
interface UserSelectedListener {
    fun onUserClicked(user: User)
}