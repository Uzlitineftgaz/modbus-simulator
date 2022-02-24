package uz.liti.modbussimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.liti.modbussimulator.model.Server;

public interface ServerRepository extends JpaRepository<Server,Long> {
}
