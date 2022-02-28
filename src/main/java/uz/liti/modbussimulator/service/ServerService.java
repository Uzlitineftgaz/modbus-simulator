package uz.liti.modbussimulator.service;

import de.re.easymodbus.server.ModbusServer;
import org.springframework.stereotype.Service;
import uz.liti.modbussimulator.model.Register;
import uz.liti.modbussimulator.model.RegisterType;
import uz.liti.modbussimulator.model.Server;
import uz.liti.modbussimulator.repository.RegisterRepository;
import uz.liti.modbussimulator.repository.ServerRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ServerService {

    private final ServerRepository serverRepository;
    private final RegisterRepository registerRepository;

    public List<ModbusServer> serverList=new ArrayList<>();



    public ServerService(ServerRepository serverRepository, RegisterRepository registerRepository) {
        this.serverRepository = serverRepository;
        this.registerRepository = registerRepository;
    }

    public boolean save(Server server){
        try {
            serverRepository.save(server);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public Register saveInputRegister(Register register,float value){
        try {
            if (register.getAddress()%4!=0) return null;



            short[] shorts=floatToTwoInt16(value);


            register.setValue(shorts[0]);
            Register register1= Register
                    .builder()
                    .address(register.getAddress()+1)
                    .server(register.getServer())
                    .type(RegisterType.INPUT_REGISTER)
                    .value(shorts[1])
                    .build();
            Register register2= Register
                    .builder()
                    .server(register.getServer())
                    .value((short) 0)
                    .type(RegisterType.INPUT_REGISTER)
                    .address(register.getAddress()+2)
                    .build();
            Register register3= Register
                    .builder()
                    .server(register.getServer())
                    .value((short) 0)
                    .type(RegisterType.INPUT_REGISTER)
                    .address(register.getAddress()+3)
                    .build();


            register=registerRepository.save(register);
            register1.setParent(register);
            register2.setParent(register);
            register3.setParent(register);
            register1=registerRepository.save(register1);
            registerRepository.save(register2);
            registerRepository.save(register3);

            updateServers(register,registerRepository.findAllByParent(register));
            return register;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    public List<Register> findAllByServer(Server server){
        return registerRepository.findAllByServer(server);
    }



    public static float twoInt16ToFloat(short[] array){
        float v = Float.intBitsToFloat(twoInt16ToInt32(array));
        return Float.intBitsToFloat(twoInt16ToInt32(array));
    }

    public static short[] floatToTwoInt16(float num){
        return int32ToTwoInt16(Float.floatToIntBits(num));
    }

    public static int twoInt16ToInt32(short[] array){
        if (array.length!=2) return 0;
        short high =array[0]; // the high 16 bits
        short low = array[1]; // the low 16 bits
        return  (high << 16) | low;
    }

    public static short[] int32ToTwoInt16(int num){
        short firstHalf = (short) (num >> 16);
        short secondHalf = (short) (num & 0xffff);
        return new short[]{firstHalf,secondHalf};
    }



    public void updateServers(Register register,List<Register> children){
        try {
            for (ModbusServer s:serverList) {
                if (s.getPort()==register.getServer().getPort()){
                    s.inputRegisters[register.getAddress()+1]=register.getValue();
                    s.inputRegisters[register.getAddress()+2]=children.get(0).getValue();
                    s.inputRegisters[register.getAddress()+3]=children.get(1).getValue();
                    s.inputRegisters[register.getAddress()+4]=children.get(2).getValue();
                    s.start();
                    return;
                }
            }
            ModbusServer server=new ModbusServer();
            server.setPort(register.getServer().getPort());
            server.inputRegisters[register.getAddress()+1]=register.getValue();
            server.inputRegisters[register.getAddress()+2]=children.get(0).getValue();
            server.inputRegisters[register.getAddress()+3]=children.get(1).getValue();
            server.inputRegisters[register.getAddress()+4]=children.get(2).getValue();
            server.start();
            server.Listen();
            serverList.add(server);
            System.out.println(serverList);
            serverList.forEach(s->{
                System.out.println(s.getPort());
                System.out.println("AAAAAAAAAAAAAAAA:");
                System.out.println(s.getServerRunning());
            });




        }catch (Exception e){
            e.printStackTrace();
        }
    }








}
