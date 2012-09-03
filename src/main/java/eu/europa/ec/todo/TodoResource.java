package eu.europa.ec.todo;

import com.sun.jersey.api.view.Viewable;
import eu.europa.ec.todo.model.State;
import eu.europa.ec.todo.model.Todo;
import eu.europa.ec.todo.service.ServiceException;
import eu.europa.ec.todo.service.TodoService;
import java.net.URI;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/todo")
@ManagedBean
public class TodoResource {

    @EJB
    TodoService service;

    @GET
    public Viewable all() {
        List<Todo> all = service.all();        
        return new Viewable("/index", all);
    }

    @GET
    @Path("/top")
    public Viewable top() {
        List<Todo> all = service.top(5);
        return new Viewable("/top", all);        
    }
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response create(@FormParam("description") String description) throws ServiceException {        
        System.out.println("DESC: " + description);
        
        if (description != null && !description.isEmpty()) {
            Todo todo = new Todo(description);
            service.create(todo);
        }
        
        URI uri = UriBuilder.fromResource(TodoResource.class).build();
        return Response.seeOther(uri).build();
    }

    @GET
    @Path("/{id}")
    public Viewable details(@PathParam("id") Long id) {
        Todo todo = service.get(id);
        return new Viewable("/details", todo);
    }

    @POST
    @Path("/{id}")
    @Consumes("application/x-www-form-urlencoded")
    public Response update(@FormParam("action") String action, @PathParam("id") Long id) throws ServiceException 
    {
        if ("complete".equals(action)) {
            Todo todo = service.get(id);
            todo.setStatus(State.completed);
            service.update(todo);            
        } else if ("cancel".equals(action)) {
            Todo todo = service.get(id);
            todo.setStatus(State.cancelled);
            service.update(todo);            
        } else if ("reopen".equals(action)) {
            Todo todo = service.get(id);
            todo.setStatus(State.created);
            service.update(todo);            
        }

        URI uri = UriBuilder.fromResource(TodoResource.class).build();
        return Response.seeOther(uri).build();
    }     

    @GET
    @Path("/{id}/edit")
    public Viewable edit(@PathParam("id") Long id) {
        Todo todo = service.get(id);
        return new Viewable("/edit", todo);
    }
    
    @POST
    @Path("/{id}/edit")
    @Consumes("application/x-www-form-urlencoded")
    public Response update(
            @FormParam("action") String action,
            @PathParam("id") Long id,
            @FormParam("description") String description
            ) throws ServiceException 
    {        
        System.out.println("Action: " + action);        

        if ("save".equals(action)) {
            Todo todo = service.get(id);
            if (description != null && !description.isEmpty()) {
                todo.setDescription(description);
            }
            service.update(todo);
        }
        
        URI uri = UriBuilder.fromResource(TodoResource.class).build();
        return Response.seeOther(uri).build();
    }
}
