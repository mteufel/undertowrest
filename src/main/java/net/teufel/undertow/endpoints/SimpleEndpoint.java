package net.teufel.undertow.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.teufel.undertow.domain.Wanderweg;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;


@Path("/simple")
public class SimpleEndpoint {



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response simple() {

        Wanderweg weg = new Wanderweg();
        weg.setNumber(79);
        weg.setBezeichnung("Hohenweiler-Weg");

        ObjectMapper mapper = new ObjectMapper();


        try {
            return Response.ok(mapper.writeValueAsString(weg)).build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response simple(Wanderweg weg) {

        System.out.println("addWanderweg aufgerufen: " + weg.getBezeichnung());
        return Response.ok("OK").build();

    }

}
