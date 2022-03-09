package net.godaa.SpringWithMySQL.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.godaa.SpringWithMySQL.dao.ServerDao;
import net.godaa.SpringWithMySQL.model.Server;
import net.godaa.SpringWithMySQL.repo.ServerRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

import static net.godaa.SpringWithMySQL.enumeration.Status.SERVER_DOWN;
import static net.godaa.SpringWithMySQL.enumeration.Status.SERVER_UP;
import static org.springframework.data.domain.PageRequest.of;

// @RequiredArgsConstructor will create constructor for us using dependency injection
@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerService implements ServerDao {
    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new Server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }


    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepo.findAll(of(0, limit)).toList();


    }

    @Override
    public Server get(Long id) {
        log.info("Fetching servers by id: {}",id);
       return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating Server: {}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by ID: {}", id);
        serverRepo.deleteById(id);
        return Boolean.TRUE;
    }

    private String setServerImageUrl() {
        return null;
    }
}
