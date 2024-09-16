package dev.mcarr.words.viewmodels

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.mcarr.words.data.databases.AppDatabase
import dev.mcarr.words.data.repos.WordRepository
import dev.mcarr.words.enums.WordSource
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GameLoadScreenViewModelTest {

    private lateinit var context: Context
    private lateinit var model: GameLoadScreenViewModel
    private lateinit var database: AppDatabase
    private lateinit var repo: WordRepository

    @Before
    fun setup(){
        context = ApplicationProvider.getApplicationContext<Context>()
        model = GameLoadScreenViewModel()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        val dao = database.wordDao()
        repo = WordRepository.getInstance(dao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testReset(){

        // Default values
        assertEquals(false, model.success.value)
        assertEquals(false, model.error.value)
        assertEquals(1, model.downloadAttempt.value)

        // Change them
        model.success.value = true
        model.error.value = true
        model.downloadAttempt.value = 2

        // Confirm new values
        assertEquals(true, model.success.value)
        assertEquals(true, model.error.value)
        assertEquals(2, model.downloadAttempt.value)

        // Reset
        model.reset()

        // Confirm values have been reset to default
        assertEquals(false, model.success.value)
        assertEquals(false, model.error.value)
        assertEquals(1, model.downloadAttempt.value)

    }

    /**
     * Attempts to retrieve a word from the database.
     *
     * DATABASE is the default word source, and the room
     * database is empty by default. So the result should
     * be null.
     * */
    @Test
    fun getWordFromDatabase(){
        runBlocking {
            val word = model.getWord(context)
            assertNull(word)
        }
    }

    /**
     * Attempts to retrieve a word from an undefined source.
     *
     * In theory UNDEFINED should never be a possible value.
     * But if it is set somehow, then the word it produces
     * should always be null.
     * */
    @Test
    fun getWordFromUndefined(){
        model.source.value = WordSource.UNDEFINED
        runBlocking {
            val word = model.getWord(context)
            assertNull(word)
        }
    }

    /**
     * Attempts to retrieve the hard-coded test word.
     *
     * TEST is a word source used for unit testing and
     * for compose layout preview purposes.
     *
     * It is hard-coded to WORDS.
     * */
    @Test
    fun getWordFromTestDataSource(){
        model.source.value = WordSource.TEST
        runBlocking {
            val word = model.getWord(context)
            assertEquals("WORDS", word)
        }
    }

    /**
     * Performs the first-time initialization of the
     * database, loading it from an asset file.
     *
     * This function starts with an empty database,
     * populates it from data in a JSON file, then
     * confirms that there's at least 1 record.
     * */
    @Test
    fun firstTimeInitWithDatabase(){
        runBlocking {

            // The database should have 0 records by default
            assertEquals(0, repo.count())

            // Load words from the default_word_list.json file
            model.firstTimeInit(context)

            // The database should now have records
            assertNotEquals(0, repo.count())

        }
    }

    /**
     * Performs the same checks as firstTimeInitWithDatabase(),
     * but with a different data source defined.
     *
     * First-time setup is only required for the DATABASE source,
     * since the database might be empty. Other sources should
     * not be affected by the first-time setup.
     *
     * So unlike the other test, which expects the database to
     * be filled during first-time setup, this test expects
     * the database to remain empty.
     * */
    @Test
    fun firstTimeInitWithoutDatabase(){
        model.source.value = WordSource.TEST
        runBlocking {

            // The database should have 0 records by default
            assertEquals(0, repo.count())

            // Load words from the default_word_list.json file
            model.firstTimeInit(context)

            // The database should still not have any records,
            // since only the DATABASE word source can store them.
            assertEquals(0, repo.count())

        }
    }

}