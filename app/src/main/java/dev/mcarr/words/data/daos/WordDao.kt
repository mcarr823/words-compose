package dev.mcarr.words.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.mcarr.words.data.entities.Word

@Dao
interface WordDao {

    @Insert
    fun insert(row: Word): Long

    @Insert
    fun insert(rows: List<Word>)

    @Query("SELECT * FROM `${Word.TABLE_NAME}` WHERE `${Word.ID}` = :id LIMIT 1")
    fun get(id: Long): Word?

    @Query("SELECT * FROM `${Word.TABLE_NAME}` WHERE `${Word.LETTERS}` = :numLetters ORDER BY RANDOM() LIMIT 1")
    fun getRandom(numLetters: Int): Word?

    @Query("SELECT * FROM `${Word.TABLE_NAME}` ORDER BY RANDOM() LIMIT 1")
    fun getRandom(): Word?

    @Query("SELECT COUNT(*) FROM `${Word.TABLE_NAME}` WHERE `${Word.LETTERS}` = :numLetters")
    fun count(numLetters: Int): Int

    @Query("SELECT COUNT(*) FROM `${Word.TABLE_NAME}`")
    fun count(): Int

}