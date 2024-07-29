package dev.mcarr.words.interfaces

/**
 * Interface from which any word provider should inherit.
 *
 * The word provider could be a database, a http endpoint,
 * or any other type of data source we can query for words.
 * */
interface WordSourceClientInterface {

    /**
     * Get a single word from the endpoint.
     *
     * @return A word
     * */
    suspend fun getRandomWord(): String

    /**
     * Get a single word of the requested length from the endpoint.
     *
     * @param length Length of the word to request
     * @return A word of the requested length
     * */
    suspend fun getRandomWord(length: Int): String

}