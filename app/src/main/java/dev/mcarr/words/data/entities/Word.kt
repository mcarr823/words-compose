package dev.mcarr.words.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Primary Word constructor.
 *
 * This constructor should only be used by the database.
 *
 * If importing a new word, you should use the other constructor instead.
 *
 * @param id Unique auto-increment ID to identify this word in the database
 * @param text The actual word this entity relates to
 * @param letters The length of the word
 * */
@Entity(tableName = Word.TABLE_NAME)
class Word(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,

    @ColumnInfo(name = TEXT)
    val text: String,

    @ColumnInfo(name = LETTERS)
    val letters: Int,
) {

    constructor(
        text: String,
        letters: Int? = null
    ) : this(
        id = 0,
        text = text.uppercase(),
        letters = letters ?: text.length,
    )

    companion object{
        const val TABLE_NAME = "Word"
        const val ID = "w_id"
        const val TEXT = "w_text"
        const val LETTERS = "w_letters"
    }

}