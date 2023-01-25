package com.familyfirstsoftware.ServerTestBackend.repo;

import com.familyfirstsoftware.ServerTestBackend.domainOrModel.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServerRepo extends JpaRepository<Server, Long> {

    Server findByIpAddress(String ipAddress);

    Optional<Server> findById(Long id);

    // TODO: implement
    Server findByName(String name);

}
