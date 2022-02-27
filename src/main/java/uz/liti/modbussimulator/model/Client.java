package uz.liti.modbussimulator.model;

import de.re.easymodbus.modbusclient.ModbusClient;

import de.re.easymodbus.server.ModbusServer;
import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String ip;
    private int port;
    private int connectionTimeout = 500;
    private boolean active=false;

    @Transient
    private ModbusClient modbusClient=new ModbusClient();

//    @Builder
//    public Client(Long id, String name, String ipAddress, int port, int connectionTimeout) {
//        this.id = id;
//        this.name = name;
//        this.ipAddress = ipAddress;
//        this.port = port;
//        this.connectionTimeout = connectionTimeout;
//    }

    @Builder
    public Client(Long id, String name, String ip, int port, int connectionTimeout, boolean active) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.port = port;
        this.connectionTimeout = connectionTimeout;
        this.active = active;
    }

    public Client Connect(){
        try {
            System.out.println(ModbusServer.getAllStackTraces());

            modbusClient.setPort(port);
            modbusClient.setConnectionTimeout(connectionTimeout);
            modbusClient.setipAddress(ip);
            modbusClient.Connect();
            return this;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }




}
