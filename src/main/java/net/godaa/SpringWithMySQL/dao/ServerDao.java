package net.godaa.SpringWithMySQL.dao;

import net.godaa.SpringWithMySQL.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerDao {
// in this class we will define all methods that we are going to deal with

    Server create(Server server);

    Server ping(String ipAddress ) throws IOException;

    Collection<Server> list(int limit);

    Server get(Long id);

    Server update(Server server);

    Boolean delete(Long id);
}
