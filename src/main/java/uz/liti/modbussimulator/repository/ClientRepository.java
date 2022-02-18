package uz.liti.modbussimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.liti.modbussimulator.model.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
}
