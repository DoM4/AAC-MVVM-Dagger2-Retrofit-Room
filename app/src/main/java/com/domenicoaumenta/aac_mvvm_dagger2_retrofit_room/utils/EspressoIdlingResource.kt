package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils

/**
 * Created by domenicoaumenta on 2020-01-11.
 */

import androidx.test.espresso.IdlingResource

/**
 * Contains a static reference IdlingResource, and should be available only in a mock build type.
 */
object EspressoIdlingResource {

    private val RESOURCE = "GLOBAL"

    private val mCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

    val idlingResource: IdlingResource
        get() = mCountingIdlingResource

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }

    fun decrementIfNotIdle(){
        if (!EspressoIdlingResource.idlingResource.isIdleNow) {
            decrement() // Set app as idle.
        }
    }
}
