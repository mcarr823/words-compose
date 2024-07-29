package dev.mcarr.words.api.http

/**
 * HTTP client which interfaces with the random word API
 * hosted on: https://random-word.ryanrk.com
 *
 * All requests to the API return a list of strings, even if
 * only a single word is requested.
 * */
class RyanrkHttpClient : AbstractHttpClient() {

    override val baseUrl = "https://random-word.ryanrk.com/api/en"

    override suspend fun getRandomWord(): String =
        getJsonStringArraySingle("word/random")

    override suspend fun getRandomWord(length: Int): String =
        getJsonStringArraySingle("word/random/?length=$length")

}