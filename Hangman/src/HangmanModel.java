/** Models a game of Hangman.
 */
public class HangmanModel {
    /** Constants for the game's state, which you can test
     *  against the value returned by getState().
     */
    public static final Object IN_PROGRESS = "In progress",
        GAME_LOST = "Game over (lost)",
        GAME_WON    = "Game over (won)";
    
    // number of guesses before game is over
    private static final int MAX_GUESSES = 6;

    // letter to fill in for unknown unguessed letters
    private static final char UNKNOWN_LETTER = '?';
    
    // list of words to use
    private static final String[] ourWords = {
        "accretion", "boisterous", "dullard", "feigned", "haughty", "insipid",
        "noisome", "obdurate", "parsimonious", "sycophant"
    };
    

    // instance variables
    private String secretWord;
    private String guessWord;
    private String guessWordedLetters;
    private Object state;
    private int numGuesses;

    public HangmanModel() {
        reset();
    }
    
    /** Clears guesses and picks a new word, starting a new game. */
    public void reset() {
        int rand = (int) (Math.random() * ourWords.length);
        
        secretWord = ourWords[rand];
        int wordLength = secretWord.length();
        guessWord = "";
        for (int i = 0; i < wordLength; i++) {
            guessWord += UNKNOWN_LETTER;
        }
        guessWordedLetters = "";
        numGuesses = MAX_GUESSES;
        state = IN_PROGRESS;
    }
    
    /** Returns a string representing the current word being guessed at.
     *    For example, if the current word is "banana", the value returned by this 
     *    method will be "banana".
     *
     *    @return the answer string.
     */
    public String getSecretWord() {
        return secretWord;
    }

    /** Returns a string representing the current guesses made in this game.
     *    For example, if the current word is "banana" and the user has guessed
     *    'b', 'a' and 'q', the value returned by this method will be "ba?a?a".
     *
     *    @return the guess string.
     */
    public String getGuessWord() {
        return guessWord;
    }
    
    /** Returns the number of guesses left in this game.
     *    This number will decrement every time guess(char) is called
     *    using a letter that isn't part of the current word.
     *    
     *    @return the number of guesses.
     */
    public int getNumGuessesLeft() {
        return numGuesses;
    }
    
    /** Returns the current state of this model, which will be one of
     *    Model.NO_GAME, IN_PROGRESS, GAME_LOST, or GAME_WON.
     *
     *    @return the model's state.
     */
    public Object getState() {
        return state;
    }
    
    /** Returns whether or not the player has already guessed the given
     *    letter in this game.
     * 
     *    @param letter the letter to check.
     *
     *    @return true if the given letter has been guessed, false otherwise. 
     */
    public boolean hasGuessed(char letter) {
        return guessWordedLetters.indexOf(letter) >= 0;
    }
    
    /** Guesses whether the given letter is in the current secret word.
     *    If the letter is in the word, all instances of the letter in the secret 
     *    word are revealed.    If the letter is not in the secret word, the number 
     *    of guesses is decremented by one.    This method only has effect when the 
     *    game is in progress and the given letter has 
     * 
     *    @param letter the character to guess.
     */
    public void guess(char letter) {
        // check error states:
        // - don't allow plays when game isn't in progress,
        // - make sure letter is valid (lowercase alphabet),
        // - make sure player hasn't already guessed this letter
        if (state != IN_PROGRESS
            || hasGuessed(letter)
            || !Character.isLetter(letter)
            || !Character.isLowerCase(letter)) {
            return;
        }

        // remember that the player has now guessed this letter
        guessWordedLetters += letter;
        
        // see if the letter is in the secret word or not
        if (secretWord.indexOf(letter) < 0) {    
            // incorrect guess
            numGuesses--;
            if (hasLost()) {
                state = GAME_LOST;
            }
        }
        else {
            // correct guess; replace all occurrences of the letter
            revealAll(letter);

            if (hasWon()) {
                state = GAME_WON;
            }
        }
    }
    
    // returns whether or not the player has won the current game
    private boolean hasWon() {
        return guessWord.equals(secretWord);
    }

    // returns whether or not the player has lost the current game
    private boolean hasLost() {
        return numGuesses == 0;
    }
    
    // reveals all occurrences of the given letter in the secret word
    private void revealAll(char letter) {
        int wordLength = secretWord.length();
        char[] guessLetters = guessWord.toCharArray();
        for (int i = 0; i < wordLength; i++) {
            if (secretWord.charAt(i) == letter) {
                guessLetters[i] = letter;
            }
        }
        guessWord = new String(guessLetters);
    }
}