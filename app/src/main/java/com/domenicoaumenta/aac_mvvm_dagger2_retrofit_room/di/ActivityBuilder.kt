package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.di

import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.UserActivityModule
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


/**
 * Created by domenicoaumenta on 2020-01-09.
 *
 * Activity Builder binds UserListActivityModule with MainActivity
 *
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [UserActivityModule::class])
    abstract fun bindUserListActivity() : MainActivity
}