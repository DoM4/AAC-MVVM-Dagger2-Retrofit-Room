package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room

import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.api.UserApi
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by domenicoaumenta on 2020-01-10.
 */
class UserApiTest {
    private lateinit var service : UserApi
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService(){
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
            .create(UserApi::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getUserTest() {
        val mockBody = loadJsonFromRes("get-user-response.json")
        mockWebServer.enqueue(MockResponse().setBody(mockBody))
        val response = service.getUsersByReputation(3,"desc","reputation","stackoverflow")

        val observable = response.test()
        observable.awaitTerminalEvent()
        observable.assertNoErrors()
        observable.assertValue { it.users?.isNotEmpty() == true }
        observable.assertValue { it.users?.size == 3 }
    }

    private fun loadJsonFromRes(fileName: String): String {
        val inputStream = javaClass.classLoader
            ?.getResourceAsStream("api-response/$fileName")
        val source = inputStream?.source()?.buffer()
        return source?.readString(Charsets.UTF_8) ?: "{}"
    }
}