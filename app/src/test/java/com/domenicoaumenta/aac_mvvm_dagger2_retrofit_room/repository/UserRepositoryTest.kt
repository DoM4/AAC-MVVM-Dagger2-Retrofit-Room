package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.repository

import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.api.UserApi
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.db.UserDao
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.db.UserDatabase
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.UserResponse
import com.nhaarman.mockitokotlin2.*
import io.reactivex.*
import org.junit.Before
import org.junit.Test


/**
 * Created by domenicoaumenta on 2020-01-10.
 */
class UserRepositoryTest {

    private val userApi : UserApi = mock()
    private val database : UserDatabase = mock()
    private val userDao : UserDao = mock()

    lateinit var userRepository: UserRepository


    @Before
    fun setup(){
        userRepository = UserRepository(userApi,database)
        whenever(database.userDao()).doReturn(userDao)
    }

    @Test
    fun testUserRepository(){

        val userList = listOf(
            fakeUser(11111),
            fakeUser(22222)
        )

        //Room setup
        val dbObservableData = Observable.just(userList)

        whenever(userDao.loadUsers()).thenReturn(dbObservableData)

        val userResponse = UserResponse(userList, null, null, null)
        val networkObservableData = Single.just(userResponse)

        whenever(userApi.getUsersByReputation(any(), any(), any(), any())).thenReturn(networkObservableData)

        whenever(database.runInTransaction(any())).thenCallRealMethod()

        val testableUserResponse = userRepository.getUsers().test()

        verify(userApi).getUsersByReputation(any(), any(), any(), any())
        verify(userDao).insert(eq(userList))

        networkObservableData.test().assertComplete()

        testableUserResponse.assertComplete()
        testableUserResponse.assertValue {
            it.size == 2
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