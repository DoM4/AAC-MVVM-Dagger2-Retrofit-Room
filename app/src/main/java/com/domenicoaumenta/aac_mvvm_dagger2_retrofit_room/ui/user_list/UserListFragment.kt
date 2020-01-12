package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.R
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.EspressoIdlingResource
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_list_fragment.*
import javax.inject.Inject


/**
 * Created by domenicoaumenta on 2020-01-09.
 */
class UserListFragment : DaggerFragment(){

    @Inject lateinit var viewModelFactory : ViewModelFactory

    private lateinit var userListViewModel : UserListViewModel

    private lateinit var userAdapter: UsersAdapter

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

        userListViewModel.getUserList().observe(this, Observer { list ->
            userAdapter.setUsers(list.sortedByDescending { it.reputation })
            pbUserListActivity.visibility = GONE
            EspressoIdlingResource.decrementIfNotIdle()
        })

        userListViewModel.onError.observe(this, Observer {
                    pbUserListActivity.visibility = GONE
                    Toast.makeText(context,getString(R.string.generale_error_message),Toast.LENGTH_SHORT).show()
                    EspressoIdlingResource.decrementIfNotIdle()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userSelectedListener = activity as? UserSelectedListener
        userAdapter = UsersAdapter(userSelectedListener)
        rvUserListActivity.adapter = userAdapter

        userListViewModel.showUserListFromNetwork()
    }
}