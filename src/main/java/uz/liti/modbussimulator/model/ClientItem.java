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

    public float getFloatHighLow(){
        try {
            Client client = this.client.Connect();
            int[] ints = client.getModbusClient().ReadInputRegisters(address, quantity);
            return ModbusClient.ConvertRegistersToFloat(ints, ModbusClient.RegisterOrder.HighLow);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }
}
