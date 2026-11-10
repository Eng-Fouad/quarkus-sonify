package io.quarkiverse.sonify.runtime.dev;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import io.quarkiverse.sonify.SonifyCustomizer;
import io.quarkiverse.sonify.runtime.SonifyConfig;
import io.quarkus.arc.Arc;
import io.quarkus.arc.InstanceHandle;
import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

@Recorder
public class SonifyRecorder {

    private static final Logger LOG = Logger.getLogger(SonifyRecorder.class);

    private final RuntimeValue<SonifyConfig> config;

    public SonifyRecorder(RuntimeValue<SonifyConfig> config) {
        this.config = config;
    }

    public Handler<RoutingContext> requestHandler() {
        return new SonifyRequestHandler();
    }

    /**
     * Runs on every start of the application, so a change of the configuration is heard after a live reload.
     */
    public void started() {
        SonifyEngine engine = SonifyEngine.get();
        LOG.debugf("Sonify so far: %d requests, %d responses, %d custom sounds", engine.requestCount(),
                engine.responseCount(), engine.customCount());
        List<SonifyCustomizer> customizers = new ArrayList<>();
        for (InstanceHandle<SonifyCustomizer> handle : Arc.container().listAll(SonifyCustomizer.class)) {
            customizers.add(handle.get());
        }
        // sound files are resources of the application: its class loader is the context class loader here
        engine.configure(config.getValue(), Thread.currentThread().getContextClassLoader(), customizers);
        engine.chime();
    }
}
