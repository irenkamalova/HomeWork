package ru.sbt.kamalova.terminal;

import ru.sbt.kamalova.terminal.exceptions.AccountIsLockedException;
import ru.sbt.kamalova.terminal.exceptions.IllegalPinException;
import ru.sbt.kamalova.terminal.exceptions.InvalidPinException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Irina Kamalova on 12.11.16.
 */
public class Main {
    private static Terminal terminal;
    private static List<Count> list = new ArrayList<>();

    public static void initTerminal() {
        TerminalServer terminalServer = new TerminalServer();
        Map<Short, Count> m = new HashMap<>();
        list.add(new Count("1231239128", 0));
        list.add(new Count("1431239128", 0));
        list.add(new Count("1231339128", 0));
        list.add(new Count("1231239128", 0));
        list.add(new Count("1223239128", 0));
        list.add(new Count("1234423128", 0));

        m.put((short) 1234, list.get(0));
        m.put((short) 3234, list.get(1));
        m.put((short) 7234, list.get(2));
        m.put((short) 1434, list.get(3));
        m.put((short) 1324, list.get(4));
        m.put((short) 1254, list.get(5));
        PinValidator pinValidator = new PinValidator(m);
        terminal = new TerminalImpl(terminalServer, pinValidator);
    }

    public static void main(String[] args) {

        initTerminal();
        terminal.startSession(list.get(5));
        System.out.println("Карта 1234423128");
        Scanner sc = new Scanner(System.in);
        System.out.println("0 - ввести пин");
        System.out.println("1 - проверить счет");
        System.out.println("2 - внести наличные");
        System.out.println("3 - снять наличные");
        short pin = 0;
        while (sc.hasNext()) {

            try {
                switch (sc.nextInt()) {
                    case 0:
                        pin = sc.nextShort();
                        terminal.checkPin(pin);
                        break;
                    case 1:
                        terminal.checkCount();
                        break;
                    case 2:
                        System.out.println("Введите сумму");
                        terminal.setMoney(sc.nextInt());
                        break;
                    case 3:
                        System.out.println("Введите сумму");
                        terminal.getMoney(sc.nextInt());
                }
            } catch (InvalidPinException e) {
                System.out.println("Неверно введен пин-код");
            } catch (AccountIsLockedException e) {
                System.out.println("Аккаунт заблокирован на 5 секунд");
            } catch (IllegalPinException e) {
                System.out.println("Введите пин-код");
            }
        }
    }
}

