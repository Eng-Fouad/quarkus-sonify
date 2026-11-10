package io.quarkiverse.sonify;

/**
 * What one sound is. The last call wins, except for {@link #tone(Wave, double, int, int)}, which adds up: several tones
 * make a motif or a chord.
 */
public interface SoundDefinition {

    /**
     * No sound at all.
     */
    void off();

    /**
     * A sound file, looked up in the resources of the application first and then on the file system. At most its first
     * two seconds are played. A file that cannot be read is logged and leaves the sound as it was.
     */
    void file(String path);

    /**
     * Audio computed by the application: mono samples between -1 and 1. At most the first two seconds are played.
     *
     * @param sampleRate the number of samples per second, such as 44100
     * @throws IllegalArgumentException if the sample rate is not positive
     */
    void samples(float[] samples, float sampleRate);

    /**
     * Adds one synthesized tone that fades out over its duration.
     *
     * @param frequency the pitch in hertz, between 20 and 20000 (ignored for {@link Wave#NOISE})
     * @param startMs when the tone starts, in milliseconds after the start of the sound: tones with the same start
     *        make a chord
     * @param durationMs how long the tone rings, in milliseconds
     * @return this definition, to add more tones
     * @throws IllegalArgumentException if a value is out of range, or the tone ends later than two seconds
     */
    SoundDefinition tone(Wave wave, double frequency, int startMs, int durationMs);
}
