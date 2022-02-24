package uz.liti.modbussimulator.model;

import de.re.easymodbus.modbusclient.ModbusClient;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class ClientItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int address;
    private int quantity;


    @ManyToOne
    private Client client;

    public float getValue(){
        try {
            Client client = this.client.Connect();
            int[] ints = this.client.getModbusClient().ReadInputRegisters(address, quantity);
//            int[] ints2 = this.client.getModbusClient().ReadInputRegisters(address, 4);
//            double s = ModbusClient.ConvertRegistersToDoublePrecisionFloat(ints2);
//            System.out.println("SSSSSSSSSSSSSSSSS "+s);
            return ModbusClient.ConvertRegistersToFloat(ints, ModbusClient.RegisterOrder.HighLow);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }


    public String getClient(){
        return client.getName();
    }
}
