package dev.mcarr.words.api.http

import org.junit.Before

class HerokuHttpClientTest : AbstractHttpClientTest() {

    // Start at length 2, because this endpoint doesn't provide
    // any 1-letter words
    override val minLength: Int = 2
    override val maxLength: Int = 6

    @Before
    fun setUp(){
        client = HerokuHttpClient()
    }

}