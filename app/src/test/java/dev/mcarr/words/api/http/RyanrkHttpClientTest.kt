package dev.mcarr.words.api.http

import org.junit.Before

class RyanrkHttpClientTest : AbstractHttpClientTest() {

    override val minLength: Int = 1
    override val maxLength: Int = 6

    @Before
    fun setUp(){
        client = RyanrkHttpClient()
    }

}