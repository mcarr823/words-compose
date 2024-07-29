package dev.mcarr.words.api.http

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HerokuHttpClientTest {

    private lateinit var client: HerokuHttpClient

    @Before
    fun setUp(){
        client = HerokuHttpClient()
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

            // Skip length=1 because the endpoint doesn't
            // provide any 1-letter words

            val word2 = client.getRandomWord(length=2)
            assertEquals(2, word2.length)

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