package nl.hu.v2iac1.rest.resource;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.hu.v2iac1.Configuration;

@Path("/secret")
@Produces(MediaType.TEXT_PLAIN)
public class SecretRestService extends AbstractRestService{

	Configuration configuration = new Configuration();
	String ses = configuration.getValue(Configuration.Key.SES);
	
	public void init(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		if(req.getSession().getAttribute("user").equals(ses)){
			
		}
		else{
			RequestDispatcher rd = null;
			rd = req.getRequestDispatcher("index.html");
			rd.forward(req, resp);
		}
	}
	
    @GET
    @Path("/")
    public Response getSecret() {

        String output = "This is the secret: " + configuration.getValue(Configuration.Key.SECRET);
        
        return Response.status(200).entity(output).build();

    }

}