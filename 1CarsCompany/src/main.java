import Session.AdminSession;
import Session.EmployeeSession;

import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        //Не поняла что подразумевается под авторизацией в данном приложении, поэтому просто запрашиваю роль
        System.out.println("Под какой ролью осуществляется вход? Введите номер");
        System.out.println("1. Оператор");
        System.out.println("2. Администратор");
        while (true) {
            try {
                int command = new Scanner(System.in).nextInt();
                switch (command) {
                    case 1: {
                        new EmployeeSession().printMenu();
                        break;
                    }
                    case 2: {
                        new AdminSession().printMenu();
                        break;
                    }
                    default: {
                        System.out.println("Некорректное значение, введите номер операции из списка выше");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректное значение, введите номер операции из списка выше");
            }
        }
    }
}
