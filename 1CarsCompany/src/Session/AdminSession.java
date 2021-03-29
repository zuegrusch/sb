package Session;

import Cars.Car;
import Company.CompanyCars;
import Driver.Driver;
import Session.EmployeeSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminSession extends EmployeeSession {

    public void printMenu() {
        System.out.println("Добро пожаловать, администратор\n");
        while (true) {
            System.out.println("\nВведите номер действия из списка:");
            System.out.println("Вывести информацию:");
            System.out.println("1. Общая стоимость расходов ГСМ");
            System.out.println("2. Расход по категории авто");
            System.out.println("3. Тип авто с наибольшей стоимостью расходов");
            System.out.println("4. Тип авто с наименьшей стоимостью расходов");
            System.out.println("5. Информация об авто");
            System.out.println("6. Информация о водителях");
            System.out.println("Внести информацию:");
            System.out.println("7. Изменить информацию о водителях");
            System.out.println("8. Изменить информацию по автомобилям");
            System.out.println("9. Посмотреть архивную информацию");
            System.out.println("10. Закончить смену");

            int command = new Scanner(System.in).nextInt();
            switch (command) {
                case 1: {
                    printAllCost();
                    continue;
                }
                case 2: {
                    printCategoriesCost();
                    continue;
                }
                case 3: {
                    printMaxCostCategory();
                    continue;
                }
                case 4: {
                    printMinCostCategory();
                    continue;
                }
                case 5: {
                    printCarsInfo();
                    continue;
                }
                case 6: {
                    printDriversInfo();
                    continue;
                }
                case 7: {
                    changeDriver();
                    continue;
                }
                case 8: {
                    changeCar();
                    continue;
                }
                case 9: {
                    openArchive();
                    continue;
                }
                case 10: {
                    closeSession();
                    break;
                }
                default: {
                    System.out.println("Некорректное значение, введите номер операции из списка выше");
                }
            }
        }
    }

    protected void changeDriver() {
        System.out.println("Выберите операцию:");
        System.out.println("1. Добавление/Перепривязка водителя");
        System.out.println("2. Изменение данных водителя");
        System.out.println("3. Удаление водителя");
        int command = new Scanner(System.in).nextInt();
        try {
            switch (command) {
                case 1: {
                    addDriver();
                    break;
                }
                case 2: {
                    changeDriverInfo(false);
                    break;
                }
                case 3: {
                    changeDriverInfo(true);
                    break;
                }
                default: {
                    throw new InputMismatchException();
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("Некорректное значение, введите номер из списка выше");
        }

    }

    protected void changeDriverInfo(boolean deleteFlag) {
        System.out.println("Выберите номер интересующего водителя");
        ArrayList<Driver> drivers = company.getDrivers();
        if (drivers.size() == 0) {
            System.out.println("Водители не найдены");
        } else {
            for (int i = 0; i < drivers.size(); i++) {
                System.out.println(i + " - " + drivers.get(i));
            }
            try {
                int driverIndex = new Scanner(System.in).nextInt();
                if (driverIndex < 0 || driverIndex >= drivers.size()) {
                    throw new InputMismatchException();
                }

                if (deleteFlag) {
                    //Удаление водителя
                    company.removeDriver(drivers.get(driverIndex));
                } else {
                    //Переименование
                    System.out.println("Введите ФИО для выбранного водителя");
                    String fio = new Scanner(System.in).nextLine();
                    drivers.get(driverIndex).setFullname(fio);
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректное значение, введите номер из списка выше");
            }
        }
    }

    protected void changeCar() {
        System.out.println("Выберите операцию:");
        System.out.println("1. Добавление/Изменение авто");
        System.out.println("2. Удаление авто");
        int command = new Scanner(System.in).nextInt();
        try {
            switch (command) {
                case 1: {
                    addCar();
                    break;
                }
                case 2: {
                    deleteCar();
                    break;
                }
                default: {
                    throw new InputMismatchException();
                }

            }
        } catch (InputMismatchException e) {
            System.out.println("Некорректное значение, введите номер из списка выше");
        }
    }

    protected void deleteCar() {
        System.out.println("Выберите автомобиль для удаления:");
        ArrayList<Car> cars = company.getCars();
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(i + " - " + cars.get(i));
        }
        try {
            int carIndex = new Scanner(System.in).nextInt();
            if (carIndex < 0 || carIndex >= cars.size()) {
                throw new InputMismatchException();
            }
            company.removeCar(cars.get(carIndex));
        } catch (InputMismatchException e) {
            System.out.println("Некорректное значение, введите номер из списка выше");
        }
    }

    protected void openArchive() {
        File directory = new File(filePath);
        File[] files = directory.listFiles(File::isFile);
        if (files != null) {
            System.out.println("Выберите номер интересующего архива по дате и времени");
            for (int i = 0; i < files.length; i++) {
                System.out.println(i + ". " + files[i].getName());
            }
            try {
                int fileIndex = new Scanner(System.in).nextInt();
                if (fileIndex < 0 || fileIndex >= files.length) {
                    throw new InputMismatchException();
                }
                File selectedFile = files[fileIndex];
                getFileSession(selectedFile);
            } catch (InputMismatchException e) {
                System.out.println("Некорректное значение, введите номер из списка выше");
            }
        } else {
            System.out.println("Архивные данные не найдены");
        }

    }

    protected void getFileSession(File file) {
        try (FileInputStream fileIn = new FileInputStream(file)) {
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            company = (CompanyCars) objectIn.readObject();
            objectIn.close();

        } catch (Exception ex) {
            System.out.println("Не удалось считать файл.");
        }
    }


}
