package dev.mcarr.words.data.repos

import android.content.Context
import dev.mcarr.words.data.daos.WordDao
import dev.mcarr.words.data.databases.AppDatabase
import dev.mcarr.words.data.entities.Word

/**
 * Repository class for interfacing with the Word objects
 * stored in the database.
 *
 * The repository should be accessed via its companion
 * getInstance(dao) function.
 *
 * eg.
 * val repo = WordRepository.getInstance(dao)
 * */
class WordRepository private constructor(
    private val dao: WordDao
) {

    fun insert(row: Word): Long {
        return dao.insert(row)
    }

    fun insert(rows: List<Word>){
        dao.insert(rows)
    }

    fun getRandom(numLetters: Int): Word? {
        return dao.getRandom(numLetters)
    }

    fun getRandom(): Word? {
        return dao.getRandom()
    }

    fun get(id: Long): Word? {
        return dao.get(id)
    }

    fun count(): Int {
        return dao.count()
    }

    fun count(numLetters: Int): Int {
        return dao.count(numLetters)
    }

    companion object{

        @Volatile private var instance: WordRepository? = null

        fun getInstance(dao: WordDao) =
            instance ?: synchronized(this) {
                instance ?: WordRepository(dao).also { instance = it }
            }

        fun getInstance(c: Context) =
            instance ?: synchronized(this) {
                instance ?: synchronized(this) {
                    val db = AppDatabase.instantiate(c)
                    val dao = db.wordDao()
                    WordRepository(dao).also { instance = it }
                }
            }

    }

}