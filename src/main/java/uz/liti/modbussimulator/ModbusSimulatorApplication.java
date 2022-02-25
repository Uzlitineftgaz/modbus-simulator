package uz.liti.modbussimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import uz.liti.modbussimulator.service.ClientService;

@EnableJpaRepositories
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class ModbusSimulatorApplication {



    public static void main(String[] args) {
        SpringApplication.run(ModbusSimulatorApplication.class, args);
    }

}
