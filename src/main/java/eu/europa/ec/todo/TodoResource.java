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
        
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response create(
            @FormParam("description") String description,
            @FormParam("done") boolean done
            ) throws ServiceException {        
        System.out.println("DESC: " + description + ", DONE: " + done);
        
        if (description != null && !description.isEmpty()) {
            Todo todo = new Todo(description);
            if (done) {
                todo.setStatus(State.closed);
            }
            service.create(todo);
        }
        
        URI uri = UriBuilder.fromResource(TodoResource.class).build();
        return Response.seeOther(uri).build();
    }

    @GET
    @Path("/{id}/edit")
    public Viewable edit(@PathParam("id") Long id) {
        System.out.println("ID: " + id);
        Todo todo = service.get(id);
        return new Viewable("/edit", todo);
    }
    
    @POST
    @Path("/{id}/edit")
    @Consumes("application/x-www-form-urlencoded")
    public Response update(
            @FormParam("action") String action,
            @PathParam("id") Long id,
            @FormParam("description") String description,
            @FormParam("done") boolean done
            ) throws ServiceException 
    {        
        System.out.println("Action: " + action);        

        if ("save".equals(action)) {
            Todo todo = service.get(id);
            if (description != null && !description.isEmpty()) {
                todo.setDescription(description);
            }
            todo.setStatus(done ? State.closed : State.created);
            service.update(todo);
        }
        
        URI uri = UriBuilder.fromResource(TodoResource.class).build();
        return Response.seeOther(uri).build();
    }
}
