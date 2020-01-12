package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.di

import android.app.Application
import android.service.autofill.UserData
import androidx.room.Room
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.api.UserApi
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.db.UserDatabase
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.BASE_URL
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.SchedulerProvider
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
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
import java.io.File
import javax.inject.Singleton


/**
 * Created by domenicoaumenta on 2020-01-09.
 */
@Module(includes = [ViewModelModule::class])
object AppModule{

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideUserApi(gson: Gson,okHttpClient: OkHttpClient) : UserApi {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserDatabase(app: Application) : UserDatabase{
        return Room
            .databaseBuilder(app, UserDatabase::class.java, "users.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}