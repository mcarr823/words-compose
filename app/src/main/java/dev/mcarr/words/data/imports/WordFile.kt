package dev.mcarr.words.data.imports

import dev.mcarr.words.data.entities.Word
import java.io.File

/**
 * Represents a file which contains some words to import.
 *
 * This is an abstract class which any file parsers should implement.
 *
 * eg. CSV file parser {@link dev.mcarr.words.data.imports.CsvFile}
 * JSON file parser {@link dev.mcarr.words.data.imports.JsonFile}
 * */
abstract class WordFile {

    /**
     * List containing the words parsed from the given file.
     * */
    val words = ArrayList<Word>()

}