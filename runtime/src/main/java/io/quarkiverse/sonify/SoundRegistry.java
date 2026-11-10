package io.quarkiverse.sonify;

/**
 * The sounds a {@link SonifyCustomizer} can define. Each method returns the definition of one sound; a sound that is
 * never defined keeps the sound of the theme.
 */
public interface SoundRegistry {

    /**
     * The sound of a request with this HTTP method, such as {@code "POST"}.
     */
    SoundDefinition method(String httpMethod);

    /**
     * The sound of a response with exactly this status code, such as {@code 404}. It wins over
     * {@link #statusClass(int)}.
     *
     * @throws IllegalArgumentException if the code is not between 100 and 599
     */
    SoundDefinition status(int statusCode);

    /**
     * The sound of every response of a class of status codes: {@code 4} is 400 to 499.
     *
     * @throws IllegalArgumentException if the digit is not between 1 and 5
     */
    SoundDefinition statusClass(int firstDigit);

    /**
     * The sound with this name, as played by {@link PlaySound} and {@link SoundPlayer}.
     */
    SoundDefinition sound(String name);
}
