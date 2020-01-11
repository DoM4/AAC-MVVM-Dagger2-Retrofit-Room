package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_details.UserDetailsFragment
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.USER_BUNDLE
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by domenicoaumenta on 2020-01-11.
 */

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class UserDetailsFragmentTest {

    lateinit var bundleForUserDetailsFragment : Bundle

    @Before
    fun setup(){
        bundleForUserDetailsFragment = Bundle().apply {
            putParcelable(USER_BUNDLE, createFakeUser())
        }
    }

    @Test
    fun testLaunchUserDetailsFragment() {
        launchFragmentInContainer<UserDetailsFragment>(bundleForUserDetailsFragment)

        onView(withId(R.id.userDetailsName)).check(matches(withText("Jon Skeet")))
        onView(withId(R.id.userDetailsReputation)).check(matches(withText("Reputation : 1156172")))

    }


    private fun createFakeUser() = User(22656,
        null,
        86,
        11683,
        543,
        1222430705,
        "Jon Skeet",
        12,
        false,
        1578736874,
        1567543324,
        "https://stackoverflow.com/users/22656/jon-skeet",
        "Reading, United Kingdom",
        "https://www.gravatar.com/avatar/6d8ebb117e8d83d74ea95fbdd0f87e13?s=128&d=identicon&r=PG",
        56,
        1156172,
        344,"registered",543,
        ""
    )
}