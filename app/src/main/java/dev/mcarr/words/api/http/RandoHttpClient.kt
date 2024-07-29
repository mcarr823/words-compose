package dev.mcarr.words.api.http

/**
 * HTTP client which interfaces with the random word API
 * hosted on: https://random-word-api.vercel.app
 *
 * All requests to the API return a list of strings, even if
 * only a single word is requested.
 * */
class RandoHttpClient : AbstractHttpClient() {

    override val baseUrl = "https://random-word-api.vercel.app/"

    override suspend fun getRandomWord(): String =
        getJsonStringArraySingle("api?words=1")

    override suspend fun getRandomWord(length: Int): String =
        getJsonStringArraySingle("api?words=1&length=$length")

}