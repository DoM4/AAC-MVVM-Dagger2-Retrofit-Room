package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list.UserListViewModel
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindUserListViewModel(userListViewModel: UserListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}