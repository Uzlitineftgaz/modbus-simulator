package uz.liti.modbussimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.liti.modbussimulator.model.Client;
import uz.liti.modbussimulator.model.ClientItem;

@Repository
public interface ClientItemRepository extends JpaRepository<ClientItem,Long> {
}
