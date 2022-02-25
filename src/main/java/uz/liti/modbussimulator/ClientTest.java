package uz.liti.modbussimulator;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import de.re.easymodbus.server.ModbusServer;
import uz.liti.modbussimulator.model.Client;

import java.io.IOException;
import java.util.Arrays;

public class ClientTest {
    public static void main(String[] args) throws IOException, ModbusException, InterruptedException {
//        ModbusClient client=new ModbusClient();
//        client.setPort(1522);
//        client.setipAddress("localhost");
//        client.setConnectionTimeout(100);
//        client.Connect();
//        System.out.println(client.isConnected());
//        System.out.println(Arrays.toString(client.ReadInputRegisters(0, 2)));



        ModbusClient client=new ModbusClient();
        client.setPort(1522);
        client.setipAddress("127.0.0.1");
        client.setConnectionTimeout(500);
        client.Connect();
        System.out.println(client.isConnected());
//        System.out.println(Arrays.toString(client.ReadInputRegisters(512, 2)));
        int[] ints = client.ReadInputRegisters(0, 2);
        int[] p={0,0};


        System.out.println(Arrays.toString(ints));
        System.out.println(ModbusClient.ConvertRegistersToFloat(ints, ModbusClient.RegisterOrder.HighLow));


//        while (true){
//            System.out.println(ModbusClient.ConvertRegistersToFloat(ints, ModbusClient.RegisterOrder.HighLow));
//
//            System.out.println(Arrays.toString(ints));
//
////
////
////            client.WriteSingleRegister(0x214,1234);
////            client.WriteSingleRegister(0x215,0);
////            client.WriteSingleRegister(0x216,0);
//////            client.WriteSingleRegister(0x217,0);
////            System.out.println(Arrays.toString(client.ReadHoldingRegisters(0x214, 2)));
////            client.WriteMultipleCoils(0x210,new boolean[]{true,true});
//            client.Connect();
//
////
////            System.out.println(ModbusClient.ConvertRegistersToFloat(client.ReadWriteMultipleRegisters(0x20c,2,0x20c,p), ModbusClient.RegisterOrder.HighLow));
//            System.out.println(ModbusClient.ConvertRegistersToFloat(ints, ModbusClient.RegisterOrder.HighLow));
//
//            Thread.sleep(1000);
//        }


    }
}
