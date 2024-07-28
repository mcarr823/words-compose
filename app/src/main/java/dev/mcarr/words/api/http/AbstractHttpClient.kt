package dev.mcarr.words.api.http

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

/**
 * Abstract class which any http endpoint clients should inherit from.
 *
 * Provides a layer of abstraction between the different endpoint
 * api implementation clients and the underlying ktor library.
 * */
abstract class AbstractHttpClient() {

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

}