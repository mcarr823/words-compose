package dev.mcarr.words.data.imports

import dev.mcarr.words.data.entities.Word
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.File
import java.io.FileInputStream

/**
 * Represents an XML file which contains some words to import.
 *
 * Expected format is a root <words> node with numberous <word>
 * nodes inside of it, each containing a word inside of it.
 *
 * eg.
 * ```
 * <words>
 *  <word>paste</word>
 *  <word>pest</word>
 *  <word>testing</word>
 *  <word>test</word>
 * </words>
 * ```
 *
 * @param file XML file containing the words to import
 * */
class XmlFile(file: File) : WordFile() {

    init{
        file.inputStream().use {
            parse(it)
        }
    }

    /**
     * Parses the content of an XML file and adds all
     * of the parsed words into the `this.words` list.
     *
     * @param inputStream File stream from which to read XML data
     */
    private fun parse(inputStream: FileInputStream) {

        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(inputStream, null)
        parser.nextTag()

        parser.require(XmlPullParser.START_TAG, null, "words")

        var text = ""

        while (parser.next() != XmlPullParser.END_DOCUMENT) {

            if (parser.name == "word") {
                if (parser.eventType == XmlPullParser.TEXT) {
                    text = parser.text
                }else if (parser.eventType == XmlPullParser.END_TAG) {
                    val word = Word(text)
                    this.words.add(word)
                }
            }

        }

    }

}