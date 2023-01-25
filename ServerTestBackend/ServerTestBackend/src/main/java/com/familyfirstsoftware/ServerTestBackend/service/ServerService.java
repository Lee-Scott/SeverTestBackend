package com.familyfirstsoftware.ServerTestBackend.service;

import com.familyfirstsoftware.ServerTestBackend.domainOrModel.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerService {

    Server create(Server server);

    Server ping(String ipAddress) throws IOException;

    // Can change to page
    Collection<Server> list(int limit);

    Server get(Long id);

    Server update(Server server);

    Boolean delete(Long id);


}
