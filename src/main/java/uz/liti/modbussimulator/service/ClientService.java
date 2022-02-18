package uz.liti.modbussimulator.service;

import org.springframework.stereotype.Service;
import uz.liti.modbussimulator.model.Client;
import uz.liti.modbussimulator.model.ClientItem;
import uz.liti.modbussimulator.repository.ClientItemRepository;
import uz.liti.modbussimulator.repository.ClientRepository;

@Service
public class ClientService {


    private final ClientRepository clientRepository;
    private final ClientItemRepository clientItemRepository;

    public ClientService(ClientRepository clientRepository, ClientItemRepository clientItemRepository) {
        this.clientRepository = clientRepository;
        this.clientItemRepository = clientItemRepository;
    }

    public void test(){
        var client = Client
                .builder()
                .ipAddress("10.10.24.50")
                .port(502)
                .name("Test")
                .connectionTimeout(500)
                .build();
        client=clientRepository.save(client);

        var clientItem=ClientItem
                .builder()
                .address(0x200)
                .quantity(2)
                .name("item")
                .client(client)
                .build();
        clientItem=clientItemRepository.save(clientItem);

        System.out.println("AAAAAAAAAAAA");
        System.out.println(clientItem.getFloatHighLow());

//
//        try {
//            client.Connect();
//            System.out.println(ModbusClient.ConvertRegistersToFloat(client.ReadInputRegisters(0x200, 2),ModbusClient.RegisterOrder.HighLow));
//            System.out.println(ModbusClient.ConvertRegistersToFloat(client.ReadInputRegisters(0x21c, 2),ModbusClient.RegisterOrder.HighLow));
//        }catch (Exception e){
//            e.printStackTrace();
//        }


    }

}
