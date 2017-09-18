package net.teufel.undertow.rest;

import net.teufel.undertow.domain.Wanderweg;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api/*")
public class JerseyApp  extends ResourceConfig {

    public JerseyApp() {
        register(JacksonFeature.class);
        packages(true, "net.teufel.undertow.endpoints");
        packages(true, "net.teufel.undertow.domain");

    }


}
