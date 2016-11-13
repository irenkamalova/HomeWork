package ru.sbt.kamalova.terminal;

import ru.sbt.kamalova.terminal.exceptions.AccountIsLockedException;
import ru.sbt.kamalova.terminal.exceptions.IllegalPinException;
import ru.sbt.kamalova.terminal.exceptions.InvalidPinException;
import ru.sbt.kamalova.terminal.exceptions.NotEnoughMoneyException;

/**
 * Created by Irina Kamalova on 12.11.16.
 */
public interface Terminal {
    void startSession(Count count);


    public void checkPin(short pin) throws InvalidPinException, AccountIsLockedException, IllegalPinException;

    void checkCount() throws AccountIsLockedException, IllegalPinException;

    void setMoney(int sum) throws AccountIsLockedException, IllegalPinException;

    void getMoney(int sum) throws AccountIsLockedException, IllegalPinException;

    void checkIfUserValid() throws AccountIsLockedException, IllegalPinException;

}
