package uz.liti.modbussimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.liti.modbussimulator.data.Company;

public interface CompanyRepo extends JpaRepository<Company,Integer> {
}
