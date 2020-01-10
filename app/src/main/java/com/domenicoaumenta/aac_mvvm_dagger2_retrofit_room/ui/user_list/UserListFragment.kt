package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.R
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_details.UserDetailsFragment
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.USER_BUNDLE
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_list_fragment.*
import javax.inject.Inject


/**
 * Created by domenicoaumenta on 2020-01-09.
 */
class UserListFragment : DaggerFragment(),UserSelectedListener{
    override fun onUserClicked(user: User) {

        val detailsFragment = activity?.supportFragmentManager?.fragments?.find {
            it is UserDetailsFragment
        } ?: UserDetailsFragment()

        detailsFragment.arguments = Bundle().apply { putParcelable(USER_BUNDLE, user) }

        activity?.supportFragmentManager?.beginTransaction()
            ?.add(R.id.screenContainer, detailsFragment)
            ?.addToBackStack(null)
            ?.commit()

    }

    @Inject lateinit var viewModelFactory : ViewModelFactory

    lateinit var userListViewModel : UserListViewModel
    lateinit var userAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_list_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userListViewModel = ViewModelProviders.of(this,viewModelFactory)[UserListViewModel::class.java]

        userListViewModel.geUserList().observe(this, Observer {
            userAdapter.setUsers(it)
        })

        userListViewModel.getLoading().observe(this, Observer {
                visibility -> pbUserListActivity.visibility = visibility
        })

        userListViewModel.getError().observe(this, Observer {
            when(it){
                true -> Toast.makeText(context,getString(R.string.generale_error_message),Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userSelectedListener = this
        userAdapter = UsersAdapter(userSelectedListener)
        rvUserListActivity.adapter = userAdapter
    }
}