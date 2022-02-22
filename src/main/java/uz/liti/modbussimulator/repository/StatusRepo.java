package uz.liti.modbussimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.liti.modbussimulator.data.Company;
import uz.liti.modbussimulator.data.Status;

public interface StatusRepo extends JpaRepository<Status,Integer> {
}
