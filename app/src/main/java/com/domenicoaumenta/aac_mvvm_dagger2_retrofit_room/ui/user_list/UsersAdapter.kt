package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.ui.user_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.R
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.utils.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*


/**
 * Created by domenicoaumenta on 2020-01-09.
 */

class UsersAdapter(val userSelectedListener: UserSelectedListener?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var users = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    internal inner class UserViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.userName?.text = user.displayName
            itemView.userReputationLabel?.text = itemView.context.getString(R.string.user_reputation,user.reputation)
            Picasso.get()
                .load(user.profileImage)
                .transform(CircleTransform())
                .into(itemView.userThumbnail)

            itemView.userContainer?.setOnClickListener {
                userSelectedListener?.onUserClicked(user.userId)
            }
        }

    }
}
