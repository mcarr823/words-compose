package dev.mcarr.words.enums

/**
 * Represents an endpoint from which this game can retrieve data.
 *
 * @param key Unique identifier for the given word source
 * */
enum class WordSource(
    key: String
) {

    UNDEFINED("UNDEFINED"),
    DATABASE("DATABASE"),
    ONLINE_HEROKU("ONLINE_HEROKU"),
    ONLINE_RANDO("ONLINE_RANDO"),
    ONLINE_RYANRK("ONLINE_RYANRK"),
    TEST("TEST")

}