package nl.hu.v2iac1.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.hu.v2iac1.Configuration;

@Path("/verysecret")
@Produces(MediaType.TEXT_PLAIN)
public class VerySecretRestService extends AbstractRestService {

	@GET
	@Path("/")
	public Response getSecret() {

		String output = "This is very secret: "
				+ configuration.getValue(Configuration.Key.VERYSECRET);
		return Response.status(200).entity(output).build();

	}

}