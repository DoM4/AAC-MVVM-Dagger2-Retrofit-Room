package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room

import android.app.Application
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


/**
 * Created by domenicoaumenta on 2020-01-09.
 */
class ACCApplication : Application(),HasAndroidInjector{

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
    @Inject
    lateinit var dispatchingAndroidInjector:
            DispatchingAndroidInjector<Any>


}