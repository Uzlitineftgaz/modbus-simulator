package uz.liti.modbussimulator;



import uz.liti.modbussimulator.storm.modbus.client.ModbusClient;

import java.util.Arrays;


public class Test2 {
    public static void main(String[] args) {

        int[] flowhr=new int[]{18102,10143};
        int[] flotdy=new int[]{17358 ,7895};
        int[] floydy=new int[]{17358 ,7895};
        int[] flomth=new int[]{17106 ,61963};
        int[] floprv=new int[]{17409 ,31508};
        int[] floacc=new int[]{0,0};
        int[] mintdy=new int[]{17409 ,33855};
        int[] minydy=new int[]{17549 ,39595};
        int[] minmth=new int[]{17296 ,2185};
        int[] minprv=new int[]{17585 ,43281};
        int[] minacc=new int[]{0,0};
        int[] dpeu=new int[]{17585 ,46285};
        int[] speu=new int[]{17002 ,12156};
        int[] tmpeu=new int[]{17448 ,5288};
        System.out.println(ModbusClient.ConvertRegistersToFloat(flowhr,ModbusClient.RegisterOrder.HighLow));
        System.out.println(ModbusClient.ConvertRegistersToFloat(flotdy,ModbusClient.RegisterOrder.HighLow));
        System.out.println(ModbusClient.ConvertRegistersToFloat(flomth,ModbusClient.RegisterOrder.HighLow));
        System.out.println(ModbusClient.ConvertRegistersToFloat(floprv,ModbusClient.RegisterOrder.HighLow));
        System.out.println(ModbusClient.ConvertRegistersToFloat(floacc,ModbusClient.RegisterOrder.HighLow));
        System.out.println(ModbusClient.ConvertRegistersToFloat(mintdy,ModbusClient.RegisterOrder.HighLow));
        System.out.println(ModbusClient.ConvertRegistersToFloat(minydy,ModbusClient.RegisterOrder.HighLow));
        System.out.println(ModbusClient.ConvertRegistersToFloat(minmth,ModbusClient.RegisterOrder.HighLow));
        System.out.println(ModbusClient.ConvertRegistersToFloat(minprv,ModbusClient.RegisterOrder.HighLow));
        System.out.println(ModbusClient.ConvertRegistersToFloat(minacc,ModbusClient.RegisterOrder.HighLow));
        System.out.println(ModbusClient.ConvertRegistersToFloat(dpeu,ModbusClient.RegisterOrder.HighLow));
        System.out.println(ModbusClient.ConvertRegistersToFloat(speu,ModbusClient.RegisterOrder.HighLow));
        System.out.println(ModbusClient.ConvertRegistersToFloat(tmpeu,ModbusClient.RegisterOrder.HighLow));


    }
}
