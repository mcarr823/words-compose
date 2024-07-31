package dev.mcarr.words.api.http

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Abstract class from which any HTTP client tests should inherit.
 *
 * Provides common tests and attributes which any HTTP clients
 * should be able to pass.
 * */
@RunWith(RobolectricTestRunner::class)
abstract class AbstractHttpClientTest {

    lateinit var client: AbstractHttpClient
    abstract val minLength: Int
    abstract val maxLength: Int

    @Test
    fun getRandomWordTest(){

        runBlocking {
            val word = client.getRandomWord()
            assertEquals(true, word.isNotBlank())
        }

    }

    @Test
    fun getRandomWordLengthTest(){

        runBlocking {

            (minLength .. maxLength).forEach { length ->
                val word = client.getRandomWord(length)
                assertEquals(length, word.length)
            }

        }

    }

}