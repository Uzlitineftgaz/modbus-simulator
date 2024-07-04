package uz.liti.modbussimulator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uz.liti.modbussimulator.service.ServerService;
import uz.liti.modbussimulator.storm.modbus.server.ModbusServer;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"server_id", "address"})})
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Register {

    @Builder
    public Register(Long id, Integer address, Short value, RegisterType type, Server server) {
        this.id = id;
        this.address = address;
        this.value = value;
        this.type = type;
        this.server = server;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Min(value = 0, message = "Address should not be less than 18")
    @Max(value = 65536, message = "Address should not be greater than 150")
    private Integer address;

    private Short value;

    @Enumerated(EnumType.STRING)
    private RegisterType type;

    @ManyToOne
    private Server server;



    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Register parent;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "parent",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Register> children;



    public float getFloatValue(){
        if (id!=null&&address!=null&&address%4==0&&children.size()==3){
            float v = ServerService.twoInt16ToFloat(helper(children));
            return v;
        }
        return 0;
    }

    private short[] helper(List<Register> children){
        short[] result=new short[2];
        try {
            result[0]=getValue();
            result[1]=children.stream().filter(c->c.address==address+1).findFirst().get().getValue();
//            result[0]=children.stream().filter(c->c.address==address+2).findFirst().get().getValue();
//            result[1]=children.stream().filter(c->c.address==address+3).findFirst().get().getValue();
            System.out.println(Arrays.toString(result));
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }


    public void run(List<Register> children){
        short[] helper = helper(children);

        Map<Thread, StackTraceElement[]> allStackTraces = ModbusServer.getAllStackTraces();
        System.out.println(allStackTraces);
        System.out.println("SIZE BEFORE ADD = "+allStackTraces.size());
        System.out.println(ModbusServer.activeCount());
        ModbusServer server=new ModbusServer();
        server.setPort(server.getPort());
        server.inputRegisters[address+2]=helper[0];
        server.inputRegisters[address+3]=helper[1];
        server.start();

        System.out.println("SIZE AFTER ADD = "+ModbusServer.getAllStackTraces().size());
        System.out.println(ModbusServer.activeCount());

    }

}
