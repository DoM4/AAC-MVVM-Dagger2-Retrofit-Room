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
data class User(
    @PrimaryKey
    val userId: Int,
    val aboutMe: String?,
    val acceptRate: Int,
    val accountId: Int,
    val answerCount: Int,
    val creationDate: Int,
    val displayName: String,
    val downVoteCount: Int,
    val isEmployee: Boolean,
    val lastAccessDate: Int,
    val lastModifiedDate: Int,
    val link: String,
    val location: String?,
    val profileImage: String,
    val questionCount: Int,
    val reputation: Int,
    val upVoteCount: Int,
    val userType: String,
    val viewCount: Int,
    val websiteUrl: String?
)