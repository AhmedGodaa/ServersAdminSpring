package net.godaa.SpringWithMySQL.dao;

import net.godaa.SpringWithMySQL.model.Server;

import java.io.IOException;
import java.util.Collection;

public interface ServerDao {
//    should be serverDao

    Server create(Server server);

    Server ping(String ipAddress ) throws IOException;

    Collection<Server> list(int limit);

    Server get(Long id);

    Server update(Server server);

    Boolean delete(Long id);
}
