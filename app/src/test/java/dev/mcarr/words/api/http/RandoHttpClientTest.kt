package dev.mcarr.words.api.http

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RandoHttpClientTest {

    private lateinit var client: RandoHttpClient

    @Before
    fun setUp() {
        client = RandoHttpClient()
    }

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

            // Skip length=1 and lenth=2 because the endpoint doesn't
            // provide any 1-letter or 2-letter words

            val word3 = client.getRandomWord(length=3)
            assertEquals(3, word3.length)

            val word4 = client.getRandomWord(length=4)
            assertEquals(4, word4.length)

            val word5 = client.getRandomWord(length=5)
            assertEquals(5, word5.length)

            val word6 = client.getRandomWord(length=6)
            assertEquals(6, word6.length)

        }

    }

}