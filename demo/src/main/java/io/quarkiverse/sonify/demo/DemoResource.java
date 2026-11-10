package io.quarkiverse.sonify.demo;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HEAD;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import io.quarkiverse.sonify.SoundPlayer;

/**
 * Every HTTP method answers on {@code /status/{code}} with that status code, so any pair of request and response
 * sounds can be tried: {@code curl -X DELETE localhost:8080/status/404}.
 */
@Path("/")
public class DemoResource {

    @Inject
    OrderService orderService;

    @Inject
    SoundPlayer soundPlayer;

    /** A custom sound from an annotation: {@code OrderService.place()} is annotated with {@code @PlaySound}. */
    @POST
    @Path("orders")
    public Response placeOrder() {
        return Response.status(201).entity("order " + orderService.place() + "\n").build();
    }

    /** A custom sound that {@code DemoSounds} defines in code. */
    @POST
    @Path("orders/ship")
    public String shipOrder() {
        soundPlayer.play("shipped");
        return "shipped\n";
    }

    /** A custom sound from code: the injected {@code SoundPlayer}. */
    @GET
    @Path("audit")
    public String audit() {
        soundPlayer.play("audit");
        return "audited\n";
    }

    @GET
    @Path("hello")
    public String hello() {
        return "hello\n";
    }

    @GET
    @Path("boom")
    public String boom() {
        throw new IllegalStateException("boom");
    }

    @GET
    @Path("status/{code}")
    public Response get(@PathParam("code") int code) {
        return Response.status(code).build();
    }

    @POST
    @Path("status/{code}")
    public Response post(@PathParam("code") int code) {
        return Response.status(code).build();
    }

    @PUT
    @Path("status/{code}")
    public Response put(@PathParam("code") int code) {
        return Response.status(code).build();
    }

    @PATCH
    @Path("status/{code}")
    public Response patch(@PathParam("code") int code) {
        return Response.status(code).build();
    }

    @DELETE
    @Path("status/{code}")
    public Response delete(@PathParam("code") int code) {
        return Response.status(code).build();
    }

    @HEAD
    @Path("status/{code}")
    public Response head(@PathParam("code") int code) {
        return Response.status(code).build();
    }

    @OPTIONS
    @Path("status/{code}")
    public Response options(@PathParam("code") int code) {
        return Response.status(code).build();
    }
}
