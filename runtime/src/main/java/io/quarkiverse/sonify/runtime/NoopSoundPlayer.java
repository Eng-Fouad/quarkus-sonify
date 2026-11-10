package io.quarkiverse.sonify.runtime;

import jakarta.inject.Singleton;

import io.quarkiverse.sonify.SoundPlayer;

/**
 * The {@link SoundPlayer} of every application that is not running in dev mode: it does nothing.
 */
@Singleton
public class NoopSoundPlayer implements SoundPlayer {

    @Override
    public void play(String name) {
    }
}
