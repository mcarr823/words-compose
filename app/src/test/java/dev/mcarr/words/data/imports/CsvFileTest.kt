package dev.mcarr.words.data.imports

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.File

@RunWith(RobolectricTestRunner::class)
class CsvFileTest {

    @Test
    fun parseFileTest(){

        val data = """
            |test
            |rest
            |pest
        """.trimMargin().trim()
        val file = File.createTempFile("test", ".csv")
        file.writeText(data)
        val csvFile = CsvFile(file)
        assertEquals(3, csvFile.words.size)

    }

}