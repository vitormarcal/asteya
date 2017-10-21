package com.vitormarcal.asteya.events.listeners;

import com.vitormarcal.asteya.events.RecursoCriadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class RecursoCriadoListener {

    @EventListener
    public void onApplicationEvent(final RecursoCriadoEvent recursoCriadoEvent) {
        final HttpServletResponse response = recursoCriadoEvent.getResponse();
        final Long id = recursoCriadoEvent.getId();

        adicionarHeaderLocation(response, id);
    }

    private void adicionarHeaderLocation(final HttpServletResponse response, final Long id) {
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
