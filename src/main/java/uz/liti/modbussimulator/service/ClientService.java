package uz.liti.modbussimulator.service;


import org.springframework.stereotype.Service;
import uz.liti.modbussimulator.model.Client;
import uz.liti.modbussimulator.model.ClientItem;
import uz.liti.modbussimulator.repository.ClientItemRepository;
import uz.liti.modbussimulator.repository.ClientRepository;
import uz.liti.modbussimulator.storm.modbus.server.ModbusServer;

import java.util.List;
import java.util.Map;

@Service
public class ClientService {


    private final ClientRepository clientRepository;
    private final ClientItemRepository clientItemRepository;

    public ClientService(ClientRepository clientRepository, ClientItemRepository clientItemRepository) {
        this.clientRepository = clientRepository;
        this.clientItemRepository = clientItemRepository;
    }





    public boolean save(Client client){
        try {
            clientRepository.save(client);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public boolean saveItem(ClientItem item){
        try {
            clientItemRepository.save(item);
            return true;
        }catch (Exception e){
            return false;
        }
    }





    public void test(){


        var server= new ModbusServer();

        server.setPort(9090);
        server.setName("Alkash");
        server.inputRegisters[0]=123;
        server.inputRegisters[1]=231;
        System.out.println(server.getServerRunning());
        server.start();

//        var client = Client
//                .builder()
//                .ip("10.10.24.50")
//                .port(502)
//                .name("Test")
//                .connectionTimeout(500)
//                .build();
//        client=clientRepository.save(client);
//
//        var clientItem=ClientItem
//                .builder()
//                .address(0x200)
//                .quantity(2)
//                .name("item")
//                .client(client)
//                .build();
//        clientItem=clientItemRepository.save(clientItem);
        var client = Client
                .builder()
                .ip("192.168.1.43")
                .port(9090)
                .name("lknklnlk")
                .connectionTimeout(500)
                .build();
//        client=clientRepository.save(client);

        var clientItem=ClientItem
                .builder()
                .address(0)
                .quantity(2)
                .name("khkhkjj")
                .client(client)
                .build();
//        clientItem=clientItemRepository.save(clientItem);

        System.out.println("AAAAAAAAAAAA");
        System.out.println(clientItem.getValue());

        server.stop();
//
//        try {
//            client.Connect();
//            System.out.println(ModbusClient.ConvertRegistersToFloat(client.ReadInputRegisters(0x200, 2),ModbusClient.RegisterOrder.HighLow));
//            System.out.println(ModbusClient.ConvertRegistersToFloat(client.ReadInputRegisters(0x21c, 2),ModbusClient.RegisterOrder.HighLow));
//        }catch (Exception e){
//            e.printStackTrace();
//        }


    }


    public void deleteClient(Client client){
        clientRepository.delete(client);
    }

    public List<ClientItem> findAllItemByClient(Client client){
        return clientItemRepository.findAllByClient(client);
    }
//
//    public Map<Boolean,String> check(Client client){
//
//    }

}
