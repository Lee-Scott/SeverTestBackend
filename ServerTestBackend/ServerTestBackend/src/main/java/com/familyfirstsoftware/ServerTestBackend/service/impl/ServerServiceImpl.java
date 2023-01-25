package com.familyfirstsoftware.ServerTestBackend.service.impl;

import com.familyfirstsoftware.ServerTestBackend.domainOrModel.Server;
import com.familyfirstsoftware.ServerTestBackend.enums.Status;
import com.familyfirstsoftware.ServerTestBackend.repo.ServerRepo;
import com.familyfirstsoftware.ServerTestBackend.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;


@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {

    @Autowired
    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        // if we reach server within timeout we set status to up else we set it to down
        server.setStatus(address.isReachable(5000) ? Status.SERVER_UP : Status.SEVER_DOWN);
        serverRepo.save(server);

        return server;
    }

    // TODO make return page instead of list / collection
    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by ID: {}", id);
        return serverRepo.findById(id).get();
    }

    /*
        if there is no id or is null it knows to create if there is and id it knows to update
     */
    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by ID: {}", id);

        serverRepo.deleteById(id);
        return Boolean.TRUE;
    }



    private String setServerImageUrl() {
        String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"};

        return ServletUriComponentsBuilder.fromCurrentContextPath().path(
                "/server/image/" + imageNames[new Random().nextInt(4)])
                .toUriString();
    }


}
