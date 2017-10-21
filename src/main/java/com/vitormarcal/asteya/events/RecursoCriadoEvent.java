package com.vitormarcal.asteya.events;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {
    private final transient Long id;
    private final transient HttpServletResponse response;

    public RecursoCriadoEvent(final Object source, final HttpServletResponse response, final Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
