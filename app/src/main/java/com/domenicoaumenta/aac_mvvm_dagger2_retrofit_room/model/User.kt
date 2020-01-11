package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by domenicoaumenta on 2020-01-09.
 */
@Entity
@Parcelize
data class User(
    @SerializedName("user_id")
    @Expose
    @PrimaryKey
    val userId: Int,
    @SerializedName("about_me")
    @Expose
    val aboutMe: String?,
    @SerializedName("accept_rate")
    @Expose
    val acceptRate: Int,
    @SerializedName("account_id")
    @Expose
    val accountId: Int,
    @SerializedName("answer_count")
    @Expose
    val answerCount: Int,
    @SerializedName("creation_date")
    @Expose
    val creationDate: Int,
    @SerializedName("display_name")
    @Expose
    val displayName: String,
    @SerializedName("down_vote_count")
    @Expose
    val downVoteCount: Int,
    @SerializedName("is_employee")
    @Expose
    val isEmployee: Boolean,
    @SerializedName("last_access_date")
    @Expose
    val lastAccessDate: Int,
    @SerializedName("last_modified_date")
    @Expose
    val lastModifiedDate: Int,
    @SerializedName("link")
    @Expose
    val link: String,
    @SerializedName("location")
    @Expose
    val location: String,
    @SerializedName("profile_image")
    @Expose
    val profileImage: String,
    @SerializedName("question_count")
    @Expose
    val questionCount: Int,
    @SerializedName("reputation")
    @Expose
    val reputation: Int,
    @SerializedName("up_vote_count")
    @Expose
    val upVoteCount: Int,
    @SerializedName("user_type")
    @Expose
    val userType: String,
    @SerializedName("view_count")
    @Expose
    val viewCount: Int,
    @SerializedName("website_url")
    @Expose
    val websiteUrl: String
) : Parcelable