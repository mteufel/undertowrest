package net.teufel.undertow.rest;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import org.glassfish.jersey.servlet.ServletContainer;

public class Application {

    public static void main(String[] args) {

        try {
            DeploymentInfo servletBuilder = Servlets.deployment()
                    .setClassLoader(Application.class.getClassLoader())
                    .setContextPath("/mss")
                    .setDeploymentName("mss.war")
                    .addServlet(Servlets.servlet("jerseyServlet", ServletContainer.class)
                            .setLoadOnStartup(1)
                            .addInitParam("javax.ws.rs.Application", JerseyApp.class.getName())
                            .addMapping("/api/*"));


            DeploymentManager manager = Servlets.defaultContainer().addDeployment(servletBuilder);
            manager.deploy();

            PathHandler path = Handlers.path(Handlers.redirect("/mss"))
                    .addPrefixPath("/mss", manager.start());

            Undertow server = Undertow.builder()
                .addHttpListener(8080, "0.0.0.0")
                .setHandler(path)
                .build();

            server.start();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
