package demo.controller;

import demo.model.User;
import demo.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService userService;

    @Inject
    public UserResource(final UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") long id) {
        User user = userService.getById(id);
        System.out.println(user);
        return Response.ok(user).build();
    }

    @POST
    public User addUser(@Valid User user) {
        System.out.println(user);
        return userService.save(user);
    }

}