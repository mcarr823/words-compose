package dev.mcarr.words.enums

/**
 * Enum for available color hints on a guessed letter.
 *
 * CORRECT = a correct guess. The given letter was correctly
 * guessed in the exact right spot.
 *
 * CORRECT_ANOTHER = a correct guess, but not the only
 * occurrence of that letter.
 * ie. That letter does belong in that place, but there's
 * another instance of that same letter in the word which
 * hasn't been guessed yet.
 *
 * WRONG_PLACEMENT = an incorrect guess, but the letter
 * does exist somewhere in the word.
 *
 * INCORRECT = flat-out wrong. The letter is not anywhere
 * in the word.
 * Either that or you have already correctly guessed all
 * occurrences of the letter.
 */
enum class Hint {
    CORRECT,
    CORRECT_ANOTHER,
    WRONG_PLACEMENT,
    INCORRECT,
    NONE
}