package io.quarkiverse.sonify.runtime.dev;

import java.util.Set;

import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import io.quarkiverse.sonify.PlaySound;
import io.quarkus.arc.ArcInvocationContext;

/**
 * Plays the sound of {@link PlaySound} when the method is called, before the method runs.
 */
@PlaySound("")
@Interceptor
@Priority(Interceptor.Priority.LIBRARY_BEFORE)
public class PlaySoundInterceptor {

    @AroundInvoke
    Object aroundInvoke(InvocationContext context) throws Exception {
        Object bindings = context.getContextData().get(ArcInvocationContext.KEY_INTERCEPTOR_BINDINGS);
        if (bindings instanceof Set<?> set) {
            for (Object binding : set) {
                if (binding instanceof PlaySound playSound) {
                    SonifyEngine.get().custom(playSound.value());
                }
            }
        }
        return context.proceed();
    }
}
