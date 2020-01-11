package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.main.MainActivity
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list.UsersAdapter
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room", appContext.packageName)
    }

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun testClickOnRecyclerViewItem() {
        launchActivity<MainActivity>()

        Espresso.onView(ViewMatchers.withId(R.id.rvUserListActivity))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<UsersAdapter.UserViewHolder>(0,
                    ViewActions.click()
                ))

        Espresso.onView(ViewMatchers.withId(R.id.userDetailsUserId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
