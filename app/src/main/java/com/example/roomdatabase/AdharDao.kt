package com.example.roomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AdharDao {
    @Query("SELECT*FROM AdharDetails")
    fun getAll():List<AdharEntity>

    @Query("SELECT * FROM AdharDetails WHERE adharNumber_id LIKE:adhar LIMIT 1")
    suspend fun findByAdharNumber(adhar: Int):AdharEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAdharDetails(adharEntity: AdharEntity)

    @Delete
    suspend fun deleteAdharDetails(adharEntity: AdharEntity)
//    @Update
//    fun updateAharDetails(adharEntity: AdharEntity)
//    @Delete
//    fun deleteAll()

    @Query("SELECT*FROM AdharDetails")
    suspend fun deleteAll() //:List<AdharEntity>

    @Query("UPDATE adhardetails SET adharNumber_id=:adharNum,adharHolderName_id=:adharName WHERE adharNumber_id LIKE:adhar")
suspend fun update(adharNum:String,adharName:String,adhar: Int)
}