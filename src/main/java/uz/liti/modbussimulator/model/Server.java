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
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true)
    private String name;

    private int port;
    private int connectionTimeout = 500;
    private boolean active=false;


    @Transient
    private ModbusServer modbusServer=new ModbusServer();


    @Builder
    public Server(Long id, String name, int port, int connectionTimeout, boolean active, ModbusServer modbusServer) {
        this.id = id;
        this.name = name;
        this.port = port;
        this.connectionTimeout = connectionTimeout;
        this.active = active;
        this.modbusServer = modbusServer;
    }
}
