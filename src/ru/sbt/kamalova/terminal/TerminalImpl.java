package ru.sbt.kamalova.terminal;

import ru.sbt.kamalova.terminal.exceptions.AccountIsLockedException;
import ru.sbt.kamalova.terminal.exceptions.IllegalPinException;
import ru.sbt.kamalova.terminal.exceptions.InvalidPinException;
import ru.sbt.kamalova.terminal.exceptions.NotEnoughMoneyException;

/**
 * Created by Irina Kamalova on 12.11.16.
 */
public class TerminalImpl implements Terminal {
    private final TerminalServer server;
    private Count currentCount;
    private int efforts = 3;
    private boolean isAccountLocked = false;
    private boolean isPinValid;
    private long accountLockedTime;

    private final PinValidator pinValidator;

    public TerminalImpl(TerminalServer server, PinValidator pinValidator) {
        this.server = server;
        this.pinValidator = pinValidator;
    }

    @Override
    public void startSession(Count count) {
        this.currentCount = count;
        isAccountLocked = false;
        isPinValid = false;
        efforts = 3;
        server.startSession(count);
    }

    @Override
    public void checkPin(short pin) throws InvalidPinException, AccountIsLockedException {
        if (isAccountLocked) {
            if (System.currentTimeMillis() - accountLockedTime > 50000) {
                isAccountLocked = false;
            }
            else {
                throw new AccountIsLockedException("Аккаунт заблокирован на 5 секунд");
            }
        }
        try {
            pinValidator.validate(pin, currentCount);
            isPinValid = true;
        } catch (InvalidPinException e) {
            if (efforts == 1) { // если это была последняя попытка
                efforts = 3;
                isAccountLocked = true;
                accountLockedTime = System.currentTimeMillis();
                throw new AccountIsLockedException("Аккаунт заблокирован на 5 секунд");
            }
            efforts--;
            System.out.println("Неверный пин-код. Осталось: " + efforts + " попыток");
        }
    }

    @Override
    public void checkCount() throws AccountIsLockedException, IllegalPinException {
        checkIfUserValid();
        System.out.println("На вашем счету: " + currentCount.showMoney());
        
    }

    @Override
    public void setMoney(int sum) throws AccountIsLockedException, IllegalPinException {
        checkIfUserValid();
        server.setMoney(sum);
    }

    @Override
    public void getMoney(int sum) throws AccountIsLockedException, IllegalPinException {
        checkIfUserValid();

        try {
            server.getMoney(sum);
        } catch (NotEnoughMoneyException e) {
            System.out.println("Недостаточно средств");
        }
    }

    @Override
    public void checkIfUserValid() throws AccountIsLockedException, IllegalPinException {
        if (isAccountLocked) {
            if (System.currentTimeMillis() - accountLockedTime > 5000) {
                isAccountLocked = false;
            }
            else {
                throw new AccountIsLockedException("Account blocked");
            }
        }
        if (!isPinValid) {
            throw new IllegalPinException("Введите пин-код");
        }
    }
}
