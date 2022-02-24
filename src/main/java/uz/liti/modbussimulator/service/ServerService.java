package uz.liti.modbussimulator.service;

import org.springframework.stereotype.Service;
import uz.liti.modbussimulator.model.Client;
import uz.liti.modbussimulator.model.Server;
import uz.liti.modbussimulator.repository.ServerRepository;

@Service
public class ServerService {

    private final ServerRepository serverRepository;

    public ServerService(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    public boolean save(Server server){
        try {
            serverRepository.save(server);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
