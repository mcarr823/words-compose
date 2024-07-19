package dev.mcarr.words.data.imports

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.File

@RunWith(RobolectricTestRunner::class)
class JsonFileTest {

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

}