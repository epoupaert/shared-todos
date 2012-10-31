package eu.europa.ec.todo;

import javax.annotation.ManagedBean;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
@ManagedBean
public class RootResource {
    
    @GET
    public Response welcome() {
        return TodoResource.allTasksRedirect();
    }
}
