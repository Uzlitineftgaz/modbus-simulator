package uz.liti.modbussimulator;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import uz.liti.modbussimulator.model.Client;

import java.io.IOException;
import java.util.Arrays;

public class ClientTest {
    public static void main(String[] args) throws IOException, ModbusException {
        ModbusClient client=new ModbusClient();
        client.setPort(1522);
        client.setipAddress("localhost");
        client.setConnectionTimeout(100);
        client.Connect();
        System.out.println(client.isConnected());
        System.out.println(Arrays.toString(client.ReadInputRegisters(0, 2)));


    }
}
