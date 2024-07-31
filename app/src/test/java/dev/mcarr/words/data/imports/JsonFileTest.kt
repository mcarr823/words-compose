package dev.mcarr.words.data.imports

import org.json.JSONException
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.File

@RunWith(RobolectricTestRunner::class)
class JsonFileTest {

    /**
     * Parse a JSON file in the first format
     * ```
     * {"words":["word"]}
     * ```
     * */
    @Test
    fun parseFileTest(){

        val data = """
            {"words":["test","pest","rest"]}
        """.trimIndent().trim()
        val file = File.createTempFile("test", ".json")
        file.writeText(data)
        val jsonFile = JsonFile(file)
        assertEquals(3, jsonFile.words.size)

    }

    /**
     * Parse a JSON file in the first format
     * ```
     * ["word"]
     * ```
     * */
    @Test
    fun parseFileAltFormatTest(){

        val data = """
            ["test","pest","rest"]
        """.trimIndent().trim()
        val file = File.createTempFile("test", ".json")
        file.writeText(data)
        val jsonFile = JsonFile(file)
        assertEquals(3, jsonFile.words.size)

    }

    /**
     * Fail to parse a broken JSON file
     * */
    @Test
    fun parseBrokenFileTest(){

        // Missing the closing square bracket
        val data = """
            ["test","pest","rest"
        """.trimIndent().trim()
        val file = File.createTempFile("test", ".json")
        file.writeText(data)

        var length = 0
        val success =
            try {
                val jsonFile = JsonFile(file)
                length = jsonFile.words.size
                true
            }catch (e: JSONException){
                false
            }
        assertEquals(0, length)
        assertEquals(false, success)

    }

}