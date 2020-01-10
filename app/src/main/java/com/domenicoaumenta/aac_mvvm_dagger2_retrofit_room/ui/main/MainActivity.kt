package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.main

import android.os.Bundle
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.R
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list.UserListFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenContainer?.let {
            //Only one fragment mode
            if (savedInstanceState == null) {
                //First time, we need to add the first fragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.screenContainer, UserListFragment())
                    .commit()
            }
        }

    }

}
