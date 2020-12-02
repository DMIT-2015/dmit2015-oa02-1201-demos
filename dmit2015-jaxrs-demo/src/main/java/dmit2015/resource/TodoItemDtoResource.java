package dmit2015.resource;

import dmit2015.model.TodoItem;
import dmit2015.model.TodoItemDto;
import dmit2015.service.TodoItemRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.*;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * * Web API with CRUD methods for managing TodoItem.
 *
 *  URI						Http Method     Request Body		                        Description
 * 	----------------------  -----------		------------------------------------------- ------------------------------------------
 *	/TodoItemsDto			POST		{"name":"Finish DMIT2015 assignment 7",         Create a new TodoItem
 *                                         	"complete":false}
 * 	/TodoItemsDto/{id}		GET			                                                Find one TodoItem with a id value
 * 	/TodoItemsDto		    GET			                                                Find all TodoItem
 * 	/TodoItemsDto/{id}      PUT             {"id":5,
 * 	                                        "name":"Submitted DMIT2015 assignment 7",   Update the TodoItem
 *                                          "complete":true}
 *
 * 	/TodoItemsDto/{id}		DELETE			                                            Remove the TodoItem
 *

 curl -i -X POST http://localhost:8080/webapi/TodoItemsDto \
 -d '{"name":"","complete":false}' \
 -H 'Content-Type:application/json'

 curl -i -X GET http://localhost:8080/webapi/TodoItemsDto

 curl -i -X GET http://localhost:8080/webapi/TodoItemsDto/1

t

 curl -i -X GET http://localhost:8080/webapi/TodoItemsDto/5

 curl -i -X DELETE http://localhost:8080/webapi/TodoItemsDto/5

 curl -i -X GET http://localhost:8080/webapi/TodoItemsDto/5

 *
 */

@ApplicationScoped
// This is a CDI-managed bean that is created only once during the life cycle of the application
@Path("TodoItemsDto")	        // All methods of this class are associated this URL path
@Consumes(MediaType.APPLICATION_JSON)	// All methods this class accept only JSON format data
@Produces(MediaType.APPLICATION_JSON)	// All methods returns data that has been converted to JSON format
public class TodoItemDtoResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private TodoItemRepository todoItemRepository;

    @POST   // POST: webapi/TodoItemsDto
    public Response postTodoItem(TodoItemDto dto) {
        if (dto == null) {
            throw new BadRequestException();
        }

        String errorMessage = validateBean(dto);
        if (errorMessage != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
        }

        TodoItem newTodoItem = mapFromDto(dto);
        todoItemRepository.add(newTodoItem);

        URI todoItemsUri = uriInfo.getAbsolutePathBuilder().path(newTodoItem.getId().toString()).build();
        return Response.created(todoItemsUri).build();
    }

    @GET    // GET: webapi/TodoItemsDto/5
    @Path("{id}")
    public Response getTodoItem(@PathParam("id") Long id) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findById(id);

        if (optionalTodoItem.isEmpty()) {
            throw new NotFoundException();
        }
        TodoItem existingTodoItem = optionalTodoItem.get();
        TodoItemDto dto = mapToDto(existingTodoItem);

        return Response.ok(dto).build();
    }

    @GET    // GET: webapi/TodoItemsDto
    public Response getTodoItems() {
        return Response.ok(todoItemRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList())).build();
    }

    @PUT    // PUT: webapi/TodoItemsDto/5
    @Path("{id}")
    public Response updateTodoItem(@PathParam("id") Long id, TodoItemDto dto) {
        if (!id.equals(dto.getId())) {
            throw new BadRequestException();
        }

        Optional<TodoItem> optionalTodoItem = todoItemRepository.findById(id);
        if (optionalTodoItem.isEmpty()) {
            throw new NotFoundException();
        }

        String errorMessage = validateBean(dto);
        if (errorMessage != null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(errorMessage).build();
        }

        todoItemRepository.update(mapFromDto(dto));

        return Response.ok(dto).build();
    }

    @DELETE // DELETE: webapi/TodoItemsDto/5
    @Path("{id}")
    public Response deleteTodoItem(@PathParam("id") Long id) {
        Optional<TodoItem> optionalTodoItem = todoItemRepository.findById(id);

        if (optionalTodoItem.isEmpty()) {
            throw new NotFoundException();
        }

        todoItemRepository.remove(id);

        return Response.noContent().build();
    }

    private String validateBean(TodoItemDto dto) {
        String errorMessage = null;
        var constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(dto);
        if (constraintViolations.size() > 0) {
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            for (ConstraintViolation<TodoItemDto> singleConstraintViolation : constraintViolations) {
                jsonObjectBuilder.add(singleConstraintViolation.getPropertyPath().toString(), singleConstraintViolation.getMessage());
            }
            errorMessage = jsonObjectBuilder.build().toString();
        }
        return errorMessage;
    }

    private TodoItemDto mapToDto(TodoItem todoItem) {
        return new TodoItemDto(todoItem.getId(), todoItem.getName(), todoItem.isComplete());
    }

    private TodoItem mapFromDto(TodoItemDto dto) {
        return new TodoItem(dto.getId(), dto.getName(), dto.isComplete());
    }

}

