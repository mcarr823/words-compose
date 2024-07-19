package dev.mcarr.words.data.imports

import dev.mcarr.words.data.entities.Word
import java.io.File

/**
 * Represents a CSV file which contains some words to import.
 *
 * Expected format is one word per line, optionally with a comma
 * and the length after that.
 *
 * eg.
 * ```
 * four,4
 * words
 * ports,5
 * ```
 *
 * @param file CSV file containing the words to import
 * */
class CsvFile(file: File) : WordFile() {

    init{
        file.bufferedReader()
            .useLines { lines ->
                lines.mapIndexedTo(this.words) { index, row ->
                    val id = index.toLong()
                    val fields = row.split(',')
                    val text = fields[0]
                    val length = fields.getOrNull(1)?.toIntOrNull() ?: text.length
                    Word(id, text, length)
                }
            }
    }

}