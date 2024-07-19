package dev.mcarr.words.data.daos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import dev.mcarr.words.data.databases.AppDatabase
import dev.mcarr.words.data.entities.Word
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class WordDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: WordDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.wordDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertWordTest(){

        // Initial count should be 0.
        val countInitial = dao.count()
        assertEquals(0, countInitial)

        // Insert a single 4 letter word.
        // Count should now be 1.
        val word = Word("test")
        dao.insert(word)
        val countAfter1Insert = dao.count()
        assertEquals(1, countAfter1Insert)

        // Insert a single 7 letter word.
        // Count should now be 2.
        val word2 = Word("testing")
        dao.insert(word2)
        val countAfter2Inserts = dao.count()
        assertEquals(2, countAfter2Inserts)

        // Count the number of 4 letter words.
        // We only inserted 1.
        val countLen4 = dao.count(4)
        assertEquals(1, countLen4)

        // Count the number of 7 letter words.
        // We only inserted 1.
        val countLen7 = dao.count(7)
        assertEquals(1, countLen7)

        // Count the number of 8 letter words.
        // We haven't inserted any.
        val countLen8 = dao.count(8)
        assertEquals(0, countLen8)

    }

    @Test
    fun randomWordTest(){

        // Insert a single word and check the id.
        val word = Word("test")
        val id = dao.insert(word)

        // Confirm that the ID matches expectations.
        // The default value of `id` in Word is 0, but the
        // database treats it as an auto-increment field, so
        // it should now be 1.
        assertEquals(1, id)

        // Retrieve that word from the database and confirm
        // that the value isn't null.
        val wNullable = dao.get(id)
        assertNotNull(wNullable)
        val w = wNullable!!

        // Retrieve a "random" word from the database.
        // There's a word in the database, so the value shouldn't be null.
        val randomWordNullable = dao.getRandom()
        assertNotNull(randomWordNullable)
        val randomWord = randomWordNullable!!

        // We've only inserted 1 word into the database, so the retrieves
        // value should be an exact match for the initially inserted one.
        assertEquals(w.text, randomWord.text)
        assertEquals(w.letters, randomWord.letters)
        assertEquals(w.id, randomWord.id)

        // Retrieve a "random" 4-letter word from the database.
        // There's only one word, and it's 4 letters, so the
        // value shouldn't be null.
        val randomWordFourNullable = dao.getRandom(4)
        assertNotNull(randomWordFourNullable)
        val randomWordFour = randomWordFourNullable!!

        // ...And again, it should be an exact match.
        assertEquals(w.text, randomWordFour.text)
        assertEquals(w.letters, randomWordFour.letters)
        assertEquals(w.id, randomWordFour.id)

        // Retrieve a random 7-letter word from the database.
        // There aren't any, so it should be null.
        val randomWordSevenNullable = dao.getRandom(7)
        assertNull(randomWordSevenNullable)

    }

}