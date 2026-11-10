package io.quarkiverse.sonify;

/**
 * The shape of a synthesized tone.
 */
public enum Wave {

    /** A pure, round tone. */
    SINE,

    /** A brighter, hollow tone. */
    TRIANGLE,

    /** Unpitched noise: a click or a hiss, depending on the duration. */
    NOISE
}
