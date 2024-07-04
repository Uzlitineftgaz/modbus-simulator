package uz.liti.modbussimulator.storm.modbus.exceptions;

public class CRCFailedException extends ModbusStormException{
    public CRCFailedException() {
    }

    public CRCFailedException(String s) {
        super(s);
    }
}
