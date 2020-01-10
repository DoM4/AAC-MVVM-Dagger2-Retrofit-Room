package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.R
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_details.UserDetailsFragment
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list.UserListFragment
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list.UserSelectedListener
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.USER_BUNDLE
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(),UserSelectedListener {

    override fun onUserClicked(user: User) {

        val detailsFragment = supportFragmentManager.fragments.find {
            it is UserDetailsFragment
        } ?: UserDetailsFragment()

        detailsFragment.arguments = Bundle().apply { putParcelable(USER_BUNDLE, user) }

        supportFragmentManager.beginTransaction()
            .add(R.id.screenContainer, detailsFragment)
            .addToBackStack(null)
            .commit()


    }
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
