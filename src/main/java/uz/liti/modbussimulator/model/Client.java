package uz.liti.modbussimulator.model;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import de.re.easymodbus.modbusclient.ReceiveDataChangedListener;
import de.re.easymodbus.modbusclient.SendDataChangedListener;

import javax.persistence.Entity;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

@Entity
public class Client extends ModbusClient {





    public Client(String ipAddress, int port) {
        super(ipAddress, port);
    }

    public Client() {
        super();
    }

    @Override
    public void Connect() throws UnknownHostException, IOException {
        super.Connect();
    }

    @Override
    public void Connect(String ipAddress, int port) throws UnknownHostException, IOException {
        super.Connect(ipAddress, port);
    }

    @Override
    public void Connect(String comPort) throws Exception {
        super.Connect(comPort);
    }

    @Override
    public boolean[] ReadDiscreteInputs(int startingAddress, int quantity) throws ModbusException, UnknownHostException, SocketException, IOException {
        return super.ReadDiscreteInputs(startingAddress, quantity);
    }

    @Override
    public boolean[] ReadCoils(int startingAddress, int quantity) throws ModbusException, UnknownHostException, SocketException, IOException {
        return super.ReadCoils(startingAddress, quantity);
    }

    @Override
    public int[] ReadHoldingRegisters(int startingAddress, int quantity) throws ModbusException, UnknownHostException, SocketException, IOException {
        return super.ReadHoldingRegisters(startingAddress, quantity);
    }

    @Override
    public int[] ReadInputRegisters(int startingAddress, int quantity) throws ModbusException, UnknownHostException, SocketException, IOException {
        return super.ReadInputRegisters(startingAddress, quantity);
    }

    @Override
    public void WriteSingleCoil(int startingAddress, boolean value) throws ModbusException, UnknownHostException, SocketException, IOException {
        super.WriteSingleCoil(startingAddress, value);
    }

    @Override
    public void WriteSingleRegister(int startingAddress, int value) throws ModbusException, UnknownHostException, SocketException, IOException {
        super.WriteSingleRegister(startingAddress, value);
    }

    @Override
    public void WriteMultipleCoils(int startingAddress, boolean[] values) throws ModbusException, UnknownHostException, SocketException, IOException {
        super.WriteMultipleCoils(startingAddress, values);
    }

    @Override
    public void WriteMultipleRegisters(int startingAddress, int[] values) throws ModbusException, UnknownHostException, SocketException, IOException {
        super.WriteMultipleRegisters(startingAddress, values);
    }

    @Override
    public int[] ReadWriteMultipleRegisters(int startingAddressRead, int quantityRead, int startingAddressWrite, int[] values) throws ModbusException, UnknownHostException, SocketException, IOException {
        return super.ReadWriteMultipleRegisters(startingAddressRead, quantityRead, startingAddressWrite, values);
    }

    @Override
    public void Disconnect() throws IOException {
        super.Disconnect();
    }

    @Override
    public boolean isConnected() {
        return super.isConnected();
    }

    @Override
    public String getipAddress() {
        return super.getipAddress();
    }

    @Override
    public void setipAddress(String ipAddress) {
        super.setipAddress(ipAddress);
    }

    @Override
    public int getPort() {
        return super.getPort();
    }

    @Override
    public void setPort(int port) {
        super.setPort(port);
    }

    @Override
    public boolean getUDPFlag() {
        return super.getUDPFlag();
    }

    @Override
    public void setUDPFlag(boolean udpFlag) {
        super.setUDPFlag(udpFlag);
    }

    @Override
    public int getConnectionTimeout() {
        return super.getConnectionTimeout();
    }

    @Override
    public void setConnectionTimeout(int connectionTimeout) {
        super.setConnectionTimeout(connectionTimeout);
    }

    @Override
    public void setSerialFlag(boolean serialflag) {
        super.setSerialFlag(serialflag);
    }

    @Override
    public boolean getSerialFlag() {
        return super.getSerialFlag();
    }

    @Override
    public void setUnitIdentifier(byte unitIdentifier) {
        super.setUnitIdentifier(unitIdentifier);
    }

    @Override
    public byte getUnitIdentifier() {
        return super.getUnitIdentifier();
    }

    @Override
    public void addReveiveDataChangedListener(ReceiveDataChangedListener toAdd) {
        super.addReveiveDataChangedListener(toAdd);
    }

    @Override
    public void addSendDataChangedListener(SendDataChangedListener toAdd) {
        super.addSendDataChangedListener(toAdd);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
