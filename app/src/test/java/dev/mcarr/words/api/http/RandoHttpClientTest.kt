package dev.mcarr.words.api.http

import org.junit.Before

class RandoHttpClientTest : AbstractHttpClientTest() {

    // Start at length 3, because this endpoint doesn't provide
    // any 1-letter or 2-letter words
    override val minLength: Int = 3
    override val maxLength: Int = 6

    @Before
    fun setUp() {
        client = RandoHttpClient()
    }

}