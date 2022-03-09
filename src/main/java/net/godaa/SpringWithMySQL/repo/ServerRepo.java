package net.godaa.SpringWithMySQL.repo;

import net.godaa.SpringWithMySQL.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long> {
//     JpaRepository have methods that we will need in our service
     Server findByIpAddress(String ipAddress);
}
