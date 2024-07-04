
package uz.liti.modbussimulator.storm.io;

public final class NoSuchPortException extends Exception {

    NoSuchPortException(String str) {
        super(str);
    }

    public NoSuchPortException() {
        super();
    }
}
