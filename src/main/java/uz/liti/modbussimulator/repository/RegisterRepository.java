package uz.liti.modbussimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.liti.modbussimulator.model.Register;
import uz.liti.modbussimulator.model.Server;

import java.util.List;
import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register,Long> {

    List<Register> findAllByServer(Server server);

    Optional<Register> findByServerAndAddress(Server server,Integer address);

    List<Register> findAllByParent(Register register);

}
