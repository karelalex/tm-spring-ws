package ru.karelin.tmspringws.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.karelin.tmspringws.soap.LoginEndpoint;
import ru.karelin.tmspringws.soap.ProjectEndpoint;
import ru.karelin.tmspringws.soap.TaskEndpoint;

import javax.xml.ws.Endpoint;

@Configuration
public class WSConfig {

    @Autowired
    private Bus bus;

    @Autowired
    LoginEndpoint loginEndpoint;

    @Autowired
    ProjectEndpoint projectEndpoint;

    @Autowired
    TaskEndpoint taskEndpoint;

    @Bean
    public Endpoint loginSoap() {
        EndpointImpl endpoint = new EndpointImpl(bus, loginEndpoint);
        endpoint.publish("/login");
        return endpoint;
    }

    @Bean
    public Endpoint projectSoap() {
        EndpointImpl endpoint = new EndpointImpl(bus, projectEndpoint);
        endpoint.publish("/project");
        return endpoint;
    }

    @Bean
    public Endpoint taskSoap() {
        EndpointImpl endpoint = new EndpointImpl(bus, taskEndpoint);
        endpoint.publish("/task");
        return endpoint;
    }
}

