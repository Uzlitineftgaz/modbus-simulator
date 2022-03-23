package uz.liti.modbussimulator.model;


import lombok.*;
import uz.maniac4j.modbus.server.ModbusServer;

import javax.persistence.*;
import java.util.Map;

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



//    @ElementCollection
//    @CollectionTable(name = "register_mapping",
//            joinColumns = {@JoinColumn(name = "server_id", referencedColumnName = "id")})
//    @MapKeyColumn(name = "address")
//    @Column(name = "registers")
//    private Map<Integer, Short> registerMap;

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
