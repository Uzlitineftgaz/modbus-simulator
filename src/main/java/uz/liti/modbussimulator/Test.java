package uz.liti.modbussimulator;




import uz.liti.modbussimulator.storm.modbus.client.ModbusClient;

import java.util.Arrays;


public class Test {
    public static void main(String[] args) {
//        ModbusServer server=new ModbusServer();
//        server.setPort(9090);
//        server.setName("Simulator");
//        server.start();
//        System.out.println(Arrays.toString(server.inputRegisters));
//        System.out.println(Arrays.toString(server.holdingRegisters));
//        ModbusServer modbusServer = new ModbusServer();
//
//        modbusServer.setPort(1522);
//
//
//
////Note that Standard Port for Modbus TCP communication is 502
//
//
//
//        modbusServer.coils[1] = true;
//
//
//        modbusServer.holdingRegisters[1] = 16710;
//        modbusServer.holdingRegisters[2] = 26214;
//        modbusServer.inputRegisters[1]=16710;
//        modbusServer.inputRegisters[2]=26214;
//        modbusServer.

//        modbusServer.

        try {
//            modbusServer.Listen();
//            System.out.println(Arrays.stream(modbusServer.inputRegisters).count());
//            System.out.println(Arrays.toString(new int[]{modbusServer.inputRegisters[1]}));
//            System.out.println(Arrays.toString(new int[]{modbusServer.holdingRegisters[1]}));
//            System.out.println(modbusServer.holdingRegisters.length);
//            System.out.println(Arrays.toString(modbusServer.getLogData()));


//            ModbusClient client=new ModbusClient("10.10.24.54",502);
            ModbusClient client=new ModbusClient("192.168.123.41",502);
            client.Connect();
            Thread.sleep(1000);
//            client.WriteSingleRegister(2007,1);

            while (true){
                for (int i = 2098; i < 2100; i++) {
                    try {
                        //                System.out.println(40000+i);
                        int[] ints = client.ReadHoldingRegisters(i, 2);
                        System.out.println(Arrays.toString(ints));
                        //Tog'risi low high
                        System.out.println("Address = "+i);
                        System.out.println(ModbusClient.ConvertRegistersToFloat(ints,ModbusClient.RegisterOrder.LowHigh));
//                client.WriteSingleRegister(i,4);
                        i=i+1;
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                        i=i-1;
                    }

                }
                System.out.println("**************************************************************");
            }




        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
