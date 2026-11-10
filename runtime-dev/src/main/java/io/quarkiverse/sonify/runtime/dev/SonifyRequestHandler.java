package io.quarkiverse.sonify.runtime.dev;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

/**
 * Plays one sound when a request arrives (by HTTP method) and one when its response is sent (by status code).
 * Requests to the non-application endpoints (Dev UI, health, metrics) are ignored: they are noise in every sense.
 */
public class SonifyRequestHandler implements Handler<RoutingContext> {

    @Override
    public void handle(RoutingContext context) {
        if (!context.normalizedPath().startsWith("/q/")) {
            SonifyEngine engine = SonifyEngine.get();
            long start = System.nanoTime();
            engine.request(context.request().method().name());
            context.addEndHandler(result -> engine.response(context.response().getStatusCode(),
                    (System.nanoTime() - start) / 1_000_000.0));
        }
        context.next();
    }
}
