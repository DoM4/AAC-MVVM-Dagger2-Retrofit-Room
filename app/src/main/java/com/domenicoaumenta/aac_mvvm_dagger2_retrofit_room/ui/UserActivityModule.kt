package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui

import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector


/**
 * Created by domenicoaumenta on 2020-01-09.
 */
@Module
class UserActivityModule {

    @Provides
    fun provideUserListViewModel(userApi: UserApi) = UserListViewModel(userApi)

}