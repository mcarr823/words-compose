package dev.mcarr.words.api.http

/**
 * HTTP client which interfaces with the random word API
 * hosted on: https://random-word-api.herokuapp.com
 *
 * All requests to the API return a list of strings, even if
 * only a single word is requested.
 * */
class HerokuHttpClient : AbstractHttpClient() {

    override val baseUrl = "https://random-word-api.herokuapp.com"

    /**
     * Retrieves all available words from the remote endpoint.
     *
     * This list is quite large, so this request should only be performed
     * once in order to create a local database of words.
     *
     * @return A list of words from the server
     * */
    suspend fun downloadAllWords(): List<String> =
        getJsonStringArray("all")

    override suspend fun getRandomWord(): String =
        getJsonStringArraySingle("word")

    override suspend fun getRandomWord(length: Int): String =
        getJsonStringArraySingle("word?length=$length")

}