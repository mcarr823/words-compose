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

        /**
         * Builds and returns an instance of the repository.
         *
         * Does NOT use the cached instance.
         * This is because the overhead of opening the database
         * is irrelevant (since the function is already being
         * passed a dao).
         *
         * So we might as well return a fresh instance of
         * the repository, just in case the cached instance
         * and the dao belong to different databases.
         * eg. in the case of in-memory databases, such as
         * during a unit test.
         * */
        fun getInstance(dao: WordDao) =
            synchronized(this) {
                WordRepository(dao).also { instance = it }
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