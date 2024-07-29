package dev.mcarr.words.api.http

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RyanrkHttpClientTest {

    private lateinit var client: RyanrkHttpClient

    @Before
    fun setUp(){
        client = RyanrkHttpClient()
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

            val word1 = client.getRandomWord(length=1)
            assertEquals(1, word1.length)

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