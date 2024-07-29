package dev.mcarr.words.api.http

import dev.mcarr.words.interfaces.WordSourceClientInterface
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import org.json.JSONArray

/**
 * Abstract class which any http endpoint clients should inherit from.
 *
 * Provides a layer of abstraction between the different endpoint
 * api implementation clients and the underlying ktor library.
 * */
abstract class AbstractHttpClient() : WordSourceClientInterface {

    /**
     * Base URL from which any HTTP requests will be performed.
     * eg. https://api.my.website
     * */
    abstract val baseUrl: String

    /**
     * Creates a HTTP client, uses it, then closes the client.
     *
     * This is used to facilitate stricter opening and closing
     * of the http client.
     *
     * @param callback Callback to invoke once the http client has
     * been created. Callback is provided with a handle on the http
     * client.
     * @return Returns the result of the callback.
     * */
    private suspend fun <T>getClient(
        callback: suspend (client: HttpClient) -> T
    ): T{
        return HttpClient(CIO).use {
            callback(it)
        }
    }

    /**
     * Performs a standard HTTP GET request.
     *
     * @return Result of the request as a String.
     * */
    suspend fun get(url: String): String {
        val response = getClient{ it.get(url) }
        return response.bodyAsText()
    }

    /**
     * Performs a request to an API endpoint, expecting
     * the response to be an array of strings.
     *
     * Example response:
     * ["camos"]
     *
     * @param query Query string to append to the end of the API endpoint.
     * @return A list of words returned from the API endpoint.
     * */
    suspend fun getJsonStringArray(query: String): List<String> {
        val url = "$baseUrl/$query"
        val str = get(url)
        val json = JSONArray(str)
        val len = json.length()
        return (0 until len).map { json.getString(it) }
    }

    /**
     * Performs a request to an API endpoint, expecting
     * the response to be an array of strings.
     *
     * Example response:
     * ["camos"]
     *
     * @param query Query string to append to the end of the API endpoint.
     * @return The first word returned from the API endpoint
     * */
    suspend fun getJsonStringArraySingle(query: String): String {
        return getJsonStringArray(query)[0]
    }

}