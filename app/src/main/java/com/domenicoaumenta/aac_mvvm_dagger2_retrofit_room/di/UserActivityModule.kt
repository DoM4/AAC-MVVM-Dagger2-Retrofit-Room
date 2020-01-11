package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.di

import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.repository.UserRepository
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list.UserListViewModel
import dagger.Module
import dagger.Provides


/**
 * Created by domenicoaumenta on 2020-01-09.
 */
@Module
class UserActivityModule {

    @Provides
    fun provideUserListViewModel(userRepository : UserRepository) =
        UserListViewModel(userRepository)

}