package ru.sbt.kamalova.terminal;

import ru.sbt.kamalova.terminal.exceptions.NotEnoughMoneyException;

/**
 * Created by Irina Kamalova on 12.11.16.
 */
public class TerminalServer {
    private Count currentCount;


    public void startSession(Count count) {
        currentCount = count;
    }

    public void setMoney(int money) {
        currentCount.setMoney(money);
    }

    public void getMoney(int money) throws NotEnoughMoneyException {
        currentCount.getMoney(money);
    }
}
