package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.di

import android.app.Application
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ACCApplication
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.main.UserFragmentBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


/**
 * Created by domenicoaumenta on 2020-01-09.
 */
@Singleton
@Component(modules = [AppModule::class,
    ActivityBuilder::class,
    AndroidSupportInjectionModule::class,
    UserFragmentBindingModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance fun application(app : Application) : Builder
        fun build() : AppComponent
    }

    fun inject(app : ACCApplication)
}