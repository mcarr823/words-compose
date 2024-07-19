package dev.mcarr.words.data.imports

import dev.mcarr.words.data.entities.Word
import org.json.JSONObject
import java.io.File

/**
 * Represents a JSON file which contains some words to import.
 *
 * Expected format is an object with a "words" parameter.
 * The "words" parameter should be an array of words.
 *
 * eg.
 * ```
 * {"words":[
 *   "fiver",
 *   "words",
 *   "ports"
 * ]}
 * ```
 *
 * @param file JSON file containing the words to import
 * */
class JsonFile(file: File) : WordFile() {

    init {
        val data = file.readText()
        val json = JSONObject(data)
        val jsonWords = json.getJSONArray("words")
        val length = jsonWords.length()
        (0 until length)
            .map(jsonWords::getString)
            .map(::Word)
            .forEach(this.words::add)
    }

}