package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_details

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.R
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.CircleTransform
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.USER_BUNDLE
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.user_details_fragment.*
import java.util.*


/**
 * Created by domenicoaumenta on 2020-01-10.
 */
class UserDetailsFragment : DaggerFragment(){

    var user : User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = arguments?.getParcelable(USER_BUNDLE)
        return inflater.inflate(R.layout.user_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateUserDetails(user)
    }

    private fun populateUserDetails(user : User?){
        Picasso.get().load(user?.profileImage).transform(CircleTransform()).into(userDetailsThumbnail)

        userDetailsName.text = user?.displayName
        userDetailsUserId.text = resources.getString(R.string.user_id,user?.userId)
        userDetailsLocation.text = resources.getString(R.string.user_location,user?.location)
        userDetailsReputation.text = resources.getString(R.string.user_reputation,user?.reputation)

        user?.creationDate?.let {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it.toLong().times(1000L)
            val membersince = DateFormat.format("dd/MM/yyyy",calendar)
            userDetailsMemberSince.text = resources.getString(R.string.user_member_since,membersince)
        }


    }
}