package com.comtrade.data.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactDao {

    @Query("SELECT * FROM contacts ORDER BY page ASC, id ASC")
    fun getContactList() : PagingSource<Int, ContactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveContact(contacts: List<ContactEntity>)

    @Query("DELETE FROM contacts")
    suspend fun clearAll()

    @Query("SELECT COUNT(*) FROM contacts")
    suspend fun getCount(): Int

}