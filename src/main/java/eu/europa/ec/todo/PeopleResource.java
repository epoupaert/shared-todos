package eu.europa.ec.todo;

import com.sun.jersey.api.view.Viewable;
import eu.europa.ec.todo.model.Person;
import eu.europa.ec.todo.service.PeopleService;
import eu.europa.ec.todo.service.ServiceException;
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

@Path("/people")
@ManagedBean
public class PeopleResource {
    
    @EJB
    private PeopleService service;

    @GET
    public Viewable all() {
        List<Person> all = service.all();        
        return new Viewable("/people/index", all);
    }
    
    @GET
    @Path("/{id}")
    public Viewable details(@PathParam("id") Long id) {
        Person person = service.get(id);
        return new Viewable("/people/details", person);
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response create(
            @FormParam("username") String username,
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName) throws ServiceException {

        Person person = new Person();
        person.setUsername(username);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        service.create(person);
        return allPeopleRedirect();
    }

    private Response allPeopleRedirect() {
        URI uri = UriBuilder.fromResource(PeopleResource.class).build();
        return Response.seeOther(uri).build();
    }

}
