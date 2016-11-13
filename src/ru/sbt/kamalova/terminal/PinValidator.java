package ru.sbt.kamalova.terminal;

import ru.sbt.kamalova.terminal.exceptions.InvalidPinException;

import java.util.Map;

/**
 * Created by Irina Kamalova on 12.11.16.
 */
public class PinValidator {

    private Map<Short, Count> map;

    public PinValidator(Map<Short, Count> map) {
        this.map = map;
    }

    public boolean validate(short pin, Count count) throws InvalidPinException {
        if (map.get(pin) != count) {
            throw new InvalidPinException("Invalid pin");
        }
        return true;
    }
}
