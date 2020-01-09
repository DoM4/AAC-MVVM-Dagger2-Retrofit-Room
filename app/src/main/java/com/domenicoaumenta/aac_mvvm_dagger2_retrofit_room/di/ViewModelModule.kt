package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.UserListViewModel
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
    abstract fun bindProductViewModel(recipeViewModel: UserListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}