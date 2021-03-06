package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.di

import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_details.UserDetailsFragment
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list.UserListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by domenicoaumenta on 2020-01-09.
 */
@Module
abstract class UserFragmentBindingModule {

    @ContributesAndroidInjector
    abstract fun provideUserListFragment() : UserListFragment

    @ContributesAndroidInjector
    abstract fun provideUserDetailsFragment() : UserDetailsFragment
}