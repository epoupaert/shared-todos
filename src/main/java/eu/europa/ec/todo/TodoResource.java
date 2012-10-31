package eu.europa.ec.todo;

import com.sun.jersey.api.view.Viewable;
import eu.europa.ec.todo.model.Person;
import eu.europa.ec.todo.model.State;
import eu.europa.ec.todo.model.Todo;
import eu.europa.ec.todo.service.PeopleService;
import eu.europa.ec.todo.service.ServiceException;
import eu.europa.ec.todo.service.TodoService;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;

@Path("/todo")
@ManagedBean
public class TodoResource {

    @EJB
    private TodoService service;

    @EJB
    private PeopleService peopleService;

    @Context
    private SecurityContext sc;

    private Person getCurrentUser() {
        String username = sc.getUserPrincipal().getName();
        Person user = peopleService.getByUsername(username);
        return user;
    }
    
    public class TasksDTO {
        private Person user;
        private List<Todo> tasks;
        public TasksDTO(Person user, List<Todo> tasks) {
            this.user = user;
            this.tasks = tasks;
        }
        public Person getUser() {
            return user;
        }
        public List<Todo> getTasks() {
            return tasks;
        }
    }
    
    @GET
    public Viewable all() {
        Person currentUser = getCurrentUser();
        List<Todo> allTasks = service.all();        
        return new Viewable("/index", new TasksDTO(currentUser, allTasks));
    }

    @GET
    @Path("/top")
    public Viewable top() {
        Person currentUser = getCurrentUser();
        List<Todo> topTasks = service.top(5);
        return new Viewable("/top",  new TasksDTO(currentUser, topTasks));        
    }
    
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response create(@FormParam("description") String description) throws ServiceException {        
        if (description != null && !description.isEmpty()) {
            Person user = getCurrentUser();
            Todo todo = new Todo(description);
            todo.setCreatedBy(user);
            todo.setOwner(user);
            service.create(todo);
        }
        return allTasksRedirect();
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
        return allTasksRedirect();
    }     

    @GET
    @Path("/{id}/edit")
    public Viewable edit(@PathParam("id") Long id) {
        Todo todo = service.get(id);
        List<Person> allPeople = peopleService.all();
        
        Map model = new HashMap();
        model.put("task", todo);
        model.put("people", allPeople);
        
        return new Viewable("/edit", model);
    }
    
    @POST
    @Path("/{id}/edit")
    @Consumes("application/x-www-form-urlencoded")
    public Response update(
            @FormParam("action") String action,
            @PathParam("id") Long id,
            @FormParam("description") String description,
            @FormParam("owner") Long ownerId
            ) throws ServiceException 
    {        
        System.out.println("Action: " + action);        

        if ("save".equals(action)) {
            Todo todo = service.get(id);
            if (description != null && !description.isEmpty()) {
                todo.setDescription(description);
            }
            
            if (ownerId == null || ownerId == -1) {
                System.out.println("Owner: [NONE]");        
                todo.setOwner(null);
            } else {
                System.out.println("Owner ID: " + ownerId);
                Person owner = peopleService.get(ownerId);
                todo.setOwner(owner);
            }
            
            service.update(todo);
        }
        return allTasksRedirect();        
    }

    public static Response allTasksRedirect() {
        URI uri = UriBuilder.fromResource(TodoResource.class).build();
        return Response.seeOther(uri).build();
    }
}
