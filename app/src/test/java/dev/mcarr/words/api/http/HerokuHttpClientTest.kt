package dev.mcarr.words.api.http

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HerokuHttpClientTest : AbstractHttpClientTest() {

    // Start at length 2, because this endpoint doesn't provide
    // any 1-letter words
    override val minLength: Int = 2
    override val maxLength: Int = 6

    @Before
    fun setUp(){
        client = HerokuHttpClient()
    }

    @Test
    fun getAllWordsTest(){
        val c = client as HerokuHttpClient

        runBlocking {
            val strings = c.downloadAllWords()
            assertEquals(true, strings.isNotEmpty())
        }

    }

}