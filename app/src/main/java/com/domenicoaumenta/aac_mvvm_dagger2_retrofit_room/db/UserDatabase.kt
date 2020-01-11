package com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.domenicoaumenta.aac_mvvm_dagger2_retrofit_room.model.User


/**
 * Created by domenicoaumenta on 2020-01-11.
 */
@Database(
    entities = [
        User::class
    ],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

}