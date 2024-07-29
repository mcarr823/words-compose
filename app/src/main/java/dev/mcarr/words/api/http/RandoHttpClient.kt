package dev.mcarr.words.api.http

class RandoHttpClient : AbstractHttpClient() {

    override val baseUrl = "https://random-word-api.vercel.app/"

    override suspend fun getRandomWord(): String =
        getJsonStringArraySingle("api?words=1")

    override suspend fun getRandomWord(length: Int): String =
        getJsonStringArraySingle("api?words=1&length=$length")

}