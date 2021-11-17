package io.humourmind.todo;

import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;

@Path("/v1/todo")
@ApplicationScoped
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class TodoResource {

	private final ITodoService todoService;

	public TodoResource(ITodoService todoService) {
		this.todoService = todoService;
	}

	@GET
	public List<Todo> findAll() {
		return todoService.findAll();
	}

	@GET
	@Path("/{id}")
	public Response findById(@PathParam UUID id) {
		Todo todo = todoService.findById(id);
		return Response.ok(todo).status((todo != null) ? OK : NOT_FOUND).build();
	}

	@POST
	public Todo create(Todo todo) {
		return todoService.save(todo);
	}

	@PUT
	public Response update(Todo todo) {
		if (todo.getId() == null) {
			return Response.ok("Todo id is empty").status(BAD_REQUEST).build();
		}
		return Response.ok(todoService.update(todo)).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteById(@PathParam UUID id) {
		todoService.deleteById(id);
		return Response.status(NO_CONTENT).build();
	}

}
