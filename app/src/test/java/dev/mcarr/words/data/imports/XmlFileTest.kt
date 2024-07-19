package dev.mcarr.words.data.imports

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.File

@RunWith(RobolectricTestRunner::class)
class XmlFileTest {

    @Test
    fun parseFileTest(){

        val data = """
            <words>
                <word>test</word>
                <word>pest</word>
                <word>rest</word>
            </words>
        """.trimIndent().trim()
        val file = File.createTempFile("test", ".xml")
        file.writeText(data)
        val xmlFile = XmlFile(file)
        assertEquals(3, xmlFile.words.size)

    }

}