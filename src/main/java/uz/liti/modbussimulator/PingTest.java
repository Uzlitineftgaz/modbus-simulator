package uz.liti.modbussimulator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;

public class PingTest {
    public static void main(String[] args) throws Exception {
//        try{
//            InetAddress address = InetAddress.getByName("192.168.1.103");
//            boolean reachable = address.isReachable(1000);
//
//            System.out.println("Is host reachable? " + reachable);
//        } catch (Exception e){
//            e.printStackTrace();
//        }

        // creating list for commands
        ArrayList<String> commandList = new ArrayList<String>();

        commandList.add("ping");
        // can be replaced by IP
        commandList.add("www.google.com");

        PingTest.commands(commandList);
    }


    static void commands(ArrayList<String> commandList)
            throws Exception
    {
        // creating the sub process, execute system command
        ProcessBuilder build = new ProcessBuilder(commandList);
        Process process = build.start();

        // to read the output
        BufferedReader input = new BufferedReader(new InputStreamReader
                (process.getInputStream()));
        BufferedReader Error = new BufferedReader(new InputStreamReader
                (process.getErrorStream()));
        String s = null;

        System.out.println("Standard output: ");
        while((s = input.readLine()) != null)
        {
            System.out.println(s);
        }
        System.out.println("error (if any): ");
        while((s = Error.readLine()) != null)
        {
            System.out.println(s);
        }
    }
}
