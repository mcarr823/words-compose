package dev.mcarr.words.data.entities

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class WordTest{

    @Test
    fun createWordTest(){

        val word = Word("test")
        assertEquals("TEST", word.text)
        assertEquals(4, word.letters)

    }

}