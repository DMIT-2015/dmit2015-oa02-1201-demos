package dmit2015.resource;

import dmit2015.model.TodoItem;
import dmit2015.service.TodoItemService;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Optional;

/**
 * Read all TodoItem
 curl -i -X GET http://localhost:8080/dmit2015-project-backend-demo/webapi/TodoItem

 * Read one TodoItem with an id of 2
 curl -i -X GET http://localhost:8080/dmit2015-project-backend-demo/webapi/TodoItem/2

 * Create an TodoItem
 curl -i -X POST http://localhost:8080/dmit2015-project-backend-demo/webapi/TodoItem \
    -d '{"name":"POST TodoItem using curl command","complete":true}' \
    -H 'Content-Type: application/json'

 curl -i -X POST http://localhost:8080/dmit2015-project-backend-demo/webapi/TodoItem/secure \
 -d '{"name":"POST TodoItem using curl command","complete":true}' \
 -H 'Content-Type: application/json' \
 -H 'Authorization: Bearer eyJraWQiOiJxdWlja3N0YXJ0LWp3dC1pc3N1ZXIiLCJ0eXAiOiJqd3QiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VyMjAxNSIsInVwbiI6InVzZXIyMDE1IiwiaXNzIjoicXVpY2tzdGFydC1qd3QtaXNzdWVyIiwiYXVkIjoiand0LWF1ZGllbmNlIiwiZ3JvdXBzIjpbIlVTRVIiXSwianRpIjoiMjQzM2I1N2QtYmQ2My00ZTZiLTkyOTktMjE0YTQzMWY1YzVjIiwiaWF0IjoxNjA4MDc0MjA5LCJleHAiOjE2MDgwODg2MDl9.ZO5ykdBBP5xuvtU-lxqBBnosSff5dFtGaRREklY0nlt3fKypbaPHx_S7Cv7alXJoLTYejVl16e2Cv9Qm1Ngtx7drPjW5wMWLIV-iflCctFHkbORYBYWuO2igl1soVCI0A-Ryvf5irm6fi7o7Ik7mUZjHfelaytmHJfkhCqD6DZn8RkXDVvTTwssxXJTEmlUhKhNkyPgQKYAkoMYuua6GSqx9K1-IRH1WSDBovA5oP2PX7CP6UhKI64btgY7yJ597j6sCX-SNU8jgxxuAavX8al-FreQfvelm7b-btRnolzUUlKGm8WCJUh-X9JlHNk1RugRP1fD3em9bsb63xfZtOg'



 * Update an TodoUpdate
 curl -i -X PUT http://localhost:8080/dmit2015-project-backend-demo/webapi/TodoItem/4 \
     -d '{"id":4, "name":"PUT TodoItem using curl command","complete":false}' \
    -H 'Content-Type: application/json'

 * Delete one TodoItem
 curl -i -X DELETE http://localhost:8080/dmit2015-project-backend-demo/webapi/TodoItem/4

 *
 *
 *
 */
@Path("TodoItem")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoItemResource {

    @Inject
    private TodoItemService currentTodoItemService;

    @Context
    private UriInfo currentUriInfo;

    @RolesAllowed("USER")
    @POST
    @Path("secure")
    public Response secureCreateTodoItem(@Valid TodoItem newTodoItem) {
        if (newTodoItem == null) {
            throw new BadRequestException();
        }
        currentTodoItemService.createTodoItem(newTodoItem);
        URI locationUri = currentUriInfo
                .getAbsolutePathBuilder()
                .path(newTodoItem.getId().toString())
                .build();
        return Response.created(locationUri).build();
    }

    @POST
    public Response createTodoItem(@Valid TodoItem newTodoItem) {
        if (newTodoItem == null) {
            throw new BadRequestException();
        }
        currentTodoItemService.createTodoItem(newTodoItem);
        URI locationUri = currentUriInfo
                .getAbsolutePathBuilder()
                .path(newTodoItem.getId().toString())
                .build();
        return Response.created(locationUri).build();
    }

    @GET
    @Path("{id}")
    public Response readOneTodoItem(@PathParam("id") Long id) {
        if (id == null) {
            throw new BadRequestException();
        }
        Optional<TodoItem> optionalTodoItem = currentTodoItemService.readOne(id);
        if (optionalTodoItem.isEmpty()) {
            throw new NotFoundException();
        }
        TodoItem existingTodoItem= optionalTodoItem.get();
        return Response.ok(existingTodoItem).build();
    }

    @GET
    public Response readAll() {
        return Response.ok(currentTodoItemService.readAll()).build();
    }

    @PUT
    @Path("{id}")
    public Response updateTodoItem(@PathParam("id") Long id, TodoItem updatedTodoItem) {
        if (id == null || !id.equals(updatedTodoItem.getId())) {
            throw new BadRequestException();
        }
        Optional<TodoItem> optionalTodoItem = currentTodoItemService.readOne(id);
        if (optionalTodoItem.isEmpty()) {
            throw new NotFoundException();
        }
        currentTodoItemService.updateTodoItem(updatedTodoItem);
        return Response.noContent().build();
    }

    @RolesAllowed("USER")
    @DELETE
    @Path("{id}")
    public Response deleteTodoItem(@PathParam("id") Long id) {
        if (id == null) {
            throw new BadRequestException();
        }
        Optional<TodoItem> optionalTodoItem = currentTodoItemService.readOne(id);
        if (optionalTodoItem.isEmpty()) {
            throw new NotFoundException();
        }
        currentTodoItemService.deleteTodoItem(id);
        return Response.noContent().build();
    }
}
