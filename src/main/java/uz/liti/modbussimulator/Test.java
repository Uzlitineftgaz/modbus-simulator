package uz.liti.modbussimulator;


import uz.maniac4j.modbus.server.ModbusServer;

import java.util.Arrays;


public class Test {
    public static void main(String[] args) {
//        ModbusServer server=new ModbusServer();
//        server.setPort(9090);
//        server.setName("Simulator");
//        server.start();
//        System.out.println(Arrays.toString(server.inputRegisters));
//        System.out.println(Arrays.toString(server.holdingRegisters));
        ModbusServer modbusServer = new ModbusServer();

        modbusServer.setPort(1522);



//Note that Standard Port for Modbus TCP communication is 502



        modbusServer.coils[1] = true;


        modbusServer.holdingRegisters[1] = 16710;
        modbusServer.holdingRegisters[2] = 26214;
        modbusServer.inputRegisters[1]=16710;
        modbusServer.inputRegisters[2]=26214;
//        modbusServer.

//        modbusServer.

        try {
            modbusServer.Listen();
            System.out.println(Arrays.stream(modbusServer.inputRegisters).count());
            System.out.println(Arrays.toString(new int[]{modbusServer.inputRegisters[1]}));
            System.out.println(Arrays.toString(new int[]{modbusServer.holdingRegisters[1]}));
            System.out.println(modbusServer.holdingRegisters.length);
            System.out.println(Arrays.toString(modbusServer.getLogData()));

        }
        catch (java.io.IOException e) {

        }
    }
}
