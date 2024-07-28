package dev.mcarr.words.api.http

import org.json.JSONArray

/**
 * HTTP client which interfaces with the random word API
 * hosted on: https://random-word-api.herokuapp.com
 * */
class HerokuHttpClient() : AbstractHttpClient() {

    private val baseUrl = "https://random-word-api.herokuapp.com"

    /**
     * Performs a request to the HerokuApp random word API endpoint.
     *
     * All requests to that API return a list of strings, even if
     * only a single word is requested.
     *
     * Example response:
     * ["camos"]
     *
     * @param query Query string to append to the end of the API endpoint.
     * @return A list of words returned from the API endpoint.
     * */
    private suspend fun getHerokuResponse(query: String): List<String> {
        val url = "$baseUrl/$query"
        val str = get(url)
        val json = JSONArray(str)
        val len = json.length()
        return (0 until len).map { json.getString(it) }
    }

    /**
     * Retrieves all available words from the remote endpoint.
     *
     * This list is quite large, so this request should only be performed
     * once in order to create a local database of words.
     *
     * @return A list of words from the server
     * */
    suspend fun downloadAllWords(): List<String> {
        return getHerokuResponse("all")
    }

    /**
     * Get a single word from the endpoint.
     *
     * @return A word
     * */
    suspend fun getRandomWord(): String {
        val words = getHerokuResponse("word")
        return words[0]
    }

    /**
     * Get a single word of the requested length from the endpoint.
     *
     * @param length Length of the word to request
     * @return A word of the requested length
     * */
    suspend fun getRandomWord(length: Int): String {
        val words = getHerokuResponse("word?length=$length")
        return words[0]
    }

}