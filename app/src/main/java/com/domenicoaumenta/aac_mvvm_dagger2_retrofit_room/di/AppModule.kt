package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.di

import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.api.UserApi
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.BASE_URL
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.SchedulerProvider
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by domenicoaumenta on 2020-01-09.
 */
@Module(includes = [ViewModelModule::class])
object AppModule{

    @Provides
    @Singleton
    fun provideSchedulerProvider() = SchedulerProvider(Schedulers.io(),
        AndroidSchedulers.mainThread())

    @Singleton
    @Provides
    fun provideUserApi() : UserApi {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
            .create(UserApi::class.java)
    }
}