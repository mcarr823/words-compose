package dev.mcarr.words.classes

import androidx.compose.ui.graphics.Color
import dev.mcarr.words.enums.Hint
import dev.mcarr.words.ui.theme.Blue
import dev.mcarr.words.ui.theme.Gray
import dev.mcarr.words.ui.theme.Green
import dev.mcarr.words.ui.theme.Orange

/**
 * A single character accompanied by a hint.
 *
 * Used to represent a single letter on the game display
 * which has been guessed.
 *
 * The hint is used to provide coloring which indicates
 * whether the guess was correct or not.
 *
 * @param letter A single character to display
 * @param hint Hint used to color the GUI element
 *
 * @see Hint
 * @see HintedString
 * */
class HintedLetter(
    val letter: String,
    val hint: Hint
) {

    fun asPair(): Pair<String, Hint> = letter to hint

    fun getTextColor(): Color =
        if (hint == Hint.NONE) Color.Black else Color.White

    fun getBgColor() = when(hint) {
        Hint.CORRECT -> Green
        Hint.CORRECT_ANOTHER -> Blue
        Hint.INCORRECT -> Gray
        Hint.WRONG_PLACEMENT -> Orange
        else -> Color.White
    }

}