package com.example.roomdatabase

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.time.Instant

@Database(entities = [AdharEntity::class], version = 1)

abstract class AdharDataBase:RoomDatabase (){
    abstract fun adharDao():AdharDao
    companion object {
        @Volatile
        private var INSTANCE :AdharDataBase?=null

        fun getDatabase(context:Context):AdharDataBase{
            val temInstances= INSTANCE
            if(temInstances!=null){
                return temInstances
            }
            synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext,AdharDataBase::class.java,"adhar_database").build()
                INSTANCE=instance
                return instance
            }
        }
    }
}
