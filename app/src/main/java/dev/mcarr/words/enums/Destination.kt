package dev.mcarr.words.enums

/**
 * Object which contains String keys for each individual
 * composable route in the app.
 *
 * These values are used to identify individual screens
 * or components, and to navigate between them from the
 * NavGraph in MainActivity.
 *
 * @see dev.mcarr.words.MainActivity
 * */
object Destination{
    const val HOME = "HOME"
    const val HOW_TO_PLAY = "HOW_TO_PLAY"
    const val PLAY_GAME = "PLAY_GAME"
    const val LOAD_GAME = "LOAD_GAME"
    const val ONLINE_SOURCE = "ONLINE_SOURCE"
    const val DOWNLOAD_SOURCE = "DOWNLOAD_SOURCE"
    const val DOWNLOAD_WORD_LIST = "DOWNLOAD_WORD_LIST"
    const val IMPORT_CSV = "IMPORT_CSV"
    const val IMPORT_JSON = "IMPORT_JSON"
    const val IMPORT_XML = "IMPORT_XML"
    const val IMPORT_TXT = "IMPORT_TXT"
    const val IMPORT_LOCAL_FILE = "IMPORT_LOCAL_FILE"
    const val PROCESS_LOCAL_FILE = "PROCESS_LOCAL_FILE"
    const val SETUP = "SETUP"
}