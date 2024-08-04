package dev.mcarr.words.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Invisible text field which handles keyboard input while the
 * player is playing the game.
 *
 * Filters out invalid characters and automatically converts
 * the string to upper case.
 *
 * Also limits the length of the input.
 *
 * @param guess Initial value of the text entry field. Should be a
 * remembered mutable string.
 * @param setGuess Callback to invoke whenever the player pressed
 * a key on the keyboard. Returned the validated input string.
 * @param targetLength The maximum length of the text the player
 * can enter.
 * @param submit Callback to invoke when the player presses Done.
 * */
@Composable
fun GuessTextField(
    guess: String,
    setGuess: (newGuess: String) -> Unit,
    targetLength: Int,
    submit: () -> Unit
) {

    TextField(

        // This is a hacky solution.
        // The textfield is 0-sized, so it's not visible
        // to the player. But it can still receive keyboard
        // input, which lets us play the game.
        // This should be replaced at some point, maybe with
        // a custom TextField which looks like a WordComponent
        modifier = Modifier
            .height(0.dp)
            .width(0.dp)
            .testTag("GuessTextField"),

        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { submit() }
        ),
        value = guess,
        onValueChange = { newGuess ->

            // Cast to uppercase
            val upperGuess = newGuess.uppercase()

            // Remove any non alpha-numeric characters
            val filteredGuess = upperGuess.filter { it.isLetter() }

            // Ensure the string is <= `targetLength`
            val trimmedGuess = filteredGuess.take(targetLength)

            setGuess(trimmedGuess)

        }
    )

}

@Preview
@Composable
fun PreviewGuessTextField(){
    PreviewComponent {
        GuessTextField(guess = "TEST", setGuess = {}, targetLength = 5) {
            // Do nothing
        }
    }
}