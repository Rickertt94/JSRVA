package nl.hu.v2iac1.rest.resource;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;

import nl.hu.v2iac1.Configuration;

/**
 * Created by Roelant Ossewaarde on 2/9/15.
 */
public abstract class AbstractRestService extends HttpServlet {
    @Inject
    Configuration configuration;
}
