package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.di

import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.api.UserApi
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list.UserListViewModel
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.SchedulerProvider
import dagger.Module
import dagger.Provides


/**
 * Created by domenicoaumenta on 2020-01-09.
 */
@Module
class UserActivityModule {

    @Provides
    fun provideUserListViewModel(userApi: UserApi, schedulerProvider: SchedulerProvider) =
        UserListViewModel(userApi,schedulerProvider)

}