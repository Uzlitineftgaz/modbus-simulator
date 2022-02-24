package uz.liti.modbussimulator;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
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
        client.setPort(502);
        client.setipAddress("10.10.24.50");
        client.setConnectionTimeout(500);
        client.Connect();
        System.out.println(client.isConnected());
//        System.out.println(Arrays.toString(client.ReadInputRegisters(512, 2)));
        int[] ints = client.ReadHoldingRegisters(0x20c, 2);
        int[] p={0,0};

//            int[] ints2 = this.client.getModbusClient().ReadInputRegisters(address, 4);
//            double s = ModbusClient.ConvertRegistersToDoublePrecisionFloat(ints2);
//            System.out.println("SSSSSSSSSSSSSSSSS "+s);

//        client.WriteSingleRegister();
        while (true){
            System.out.println(ModbusClient.ConvertRegistersToFloat(ints, ModbusClient.RegisterOrder.HighLow));

            client.WriteSingleRegister(524,3);
            client.WriteSingleRegister(525,3);
            client.Connect();

//
//            System.out.println(ModbusClient.ConvertRegistersToFloat(client.ReadWriteMultipleRegisters(0x20c,2,0x20c,p), ModbusClient.RegisterOrder.HighLow));
            System.out.println(ModbusClient.ConvertRegistersToFloat(ints, ModbusClient.RegisterOrder.HighLow));

            Thread.sleep(1000);
        }


    }
}
