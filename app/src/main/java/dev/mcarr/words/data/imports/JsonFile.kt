package dev.mcarr.words.data.imports

import dev.mcarr.words.data.entities.Word
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

/**
 * Represents a JSON file which contains some words to import.
 *
 * Expected format is an object with a "words" parameter.
 * The "words" parameter should be an array of words.
 *
 * Alternatively, it also supports an array of words
 * as the root node instead.
 *
 * eg.
 * ```
 * {"words":["fiver","words","ports"]}
 * ```
 * or
 * ```
 * ["fiver","words","ports"]
 * ```
 *
 * @param data JSON string containing the words to import
 * */
class JsonFile(data: String) : WordFile() {

    init {
        val jsonWords =
            try {
                val json = JSONObject(data)
                json.getJSONArray("words")
            }catch (e: Exception){
                JSONArray(data)
            }
        val length = jsonWords.length()
        (0 until length)
            .map(jsonWords::getString)
            .map(::Word)
            .forEach(this.words::add)
    }

    /**
     * @param file JSON file containing the words to import
     * */
    constructor(file: File) : this(file.readText())

}