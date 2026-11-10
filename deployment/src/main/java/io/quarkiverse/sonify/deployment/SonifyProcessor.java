package io.quarkiverse.sonify.deployment;

import io.quarkiverse.sonify.runtime.NoopSoundPlayer;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.IsLocalDevelopment;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

/**
 * The build steps of every launch mode. Nothing here makes a sound: see {@link SonifyDevProcessor}.
 */
class SonifyProcessor {

    private static final String FEATURE = "sonify";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    /**
     * Outside of dev mode, an injected {@code SoundPlayer} does nothing, and {@code @PlaySound} has no interceptor.
     */
    @BuildStep(onlyIfNot = IsLocalDevelopment.class)
    AdditionalBeanBuildItem silentSoundPlayer() {
        return new AdditionalBeanBuildItem(NoopSoundPlayer.class);
    }
}
