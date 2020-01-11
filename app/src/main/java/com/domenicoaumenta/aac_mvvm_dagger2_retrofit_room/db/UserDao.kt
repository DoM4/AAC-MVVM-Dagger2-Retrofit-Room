package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User
import io.reactivex.Single


/**
 * Created by domenicoaumenta on 2020-01-11.
 */
@Dao
abstract class UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(users: List<User>)


    @Query("SELECT * FROM User")
    abstract fun loadUsers(): Single<List<User>>

    @Query("SELECT * FROM User WHERE :userId = userId")
    abstract fun getUser(userId: String): Single<User>
}