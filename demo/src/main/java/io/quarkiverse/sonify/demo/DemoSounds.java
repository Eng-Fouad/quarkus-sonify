package io.quarkiverse.sonify.demo;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkiverse.sonify.SonifyCustomizer;
import io.quarkiverse.sonify.SoundRegistry;
import io.quarkiverse.sonify.Wave;

/**
 * Sounds defined in code. Only called in dev mode: a production build removes this bean as unused.
 */
@ApplicationScoped
public class DemoSounds implements SonifyCustomizer {

    @Override
    public void customize(SoundRegistry sounds) {
        // a custom sound made of three rising tones
        sounds.sound("shipped")
                .tone(Wave.SINE, 392, 0, 150)
                .tone(Wave.SINE, 523, 120, 150)
                .tone(Wave.SINE, 659, 240, 400);

        // a status code with its own sound: two flat knocks for 409 Conflict
        sounds.status(409)
                .tone(Wave.TRIANGLE, 220, 0, 120)
                .tone(Wave.TRIANGLE, 220, 160, 120);

        // computed audio: a short downward sweep
        float[] sweep = new float[8000];
        double phase = 0;
        for (int i = 0; i < sweep.length; i++) {
            phase += 2 * Math.PI * (1200 - 900.0 * i / sweep.length) / 16000;
            sweep[i] = (float) (Math.sin(phase) * 0.6 * (1 - (double) i / sweep.length));
        }
        sounds.status(410).samples(sweep, 16000);

        // application.properties also defines "order-placed", and a property wins: this line has no effect
        sounds.sound("order-placed").off();
    }
}
