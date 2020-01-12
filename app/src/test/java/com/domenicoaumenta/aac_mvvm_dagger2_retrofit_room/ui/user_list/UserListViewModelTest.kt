package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.repository.UserRepository
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Observable
import org.junit.*


/**
 * Created by domenicoaumenta on 2020-01-10.
 */
class UserListViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    private val userRepository: UserRepository = mock()

    private lateinit var userListViewModel: UserListViewModel

    @Before
    fun setup(){
        userListViewModel = UserListViewModel(userRepository)
    }

    @Test
    fun getUserResultList() {

        val userList = listOf(
            fakeUser(11111),
            fakeUser(22222)
        )
        whenever(userRepository.getUsers()).doReturn(Observable.just(userList))

        // live data observer
        val observerUserList : Observer<List<User>> = mock()

        userListViewModel.getUserList().observeForever(observerUserList)
        //Captor will intercept values emitted by live data
        val userListCaptor = argumentCaptor<List<User>>()
        userListCaptor.run {
            verify(observerUserList, times(1)).onChanged(capture())
            assert(firstValue == userList)
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