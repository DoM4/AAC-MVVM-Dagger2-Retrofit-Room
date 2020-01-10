package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.repository

import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.api.UserApi
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.UserResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test


/**
 * Created by domenicoaumenta on 2020-01-10.
 */
class UserRepositoryTest {

    private val userApi : UserApi = mock()

    lateinit var userRepository: UserRepository


    @Before
    fun setup(){
        userRepository = UserRepository(userApi)
    }

    @Test
    fun testUserRepository(){
        val userList = listOf(
            fakeUser(11111),
            fakeUser(22222)
        )

        val userResponse = UserResponse(userList, null, null, null)
        whenever(userApi.getUsersByReputation(any(), any(), any(), any())).thenReturn(Single.just(userResponse))

        val testableUserResponse = userRepository.getUsers().test()

        testableUserResponse.assertComplete()
        testableUserResponse.assertValue {
            it.users?.size == 2
        }
    }

    private fun fakeUser(userId : Int) = User(
        userId,
        null,
        0,
        0,
        0,
        0,
        "FakeUser",
        0,
        false,
        0,
        0,
        "link",
        "london",
        "",
        0,
        0,
        0,
        "admin",
        0,
        "www.test.com")
}