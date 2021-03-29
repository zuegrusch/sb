package Session;

import Cars.Car;
import Cars.CarCreator;
import Driver.Driver;
import Company.CompanyCars;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeSession {
    protected final String filePath = "data/";
    protected final SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
    protected CompanyCars company;

    public EmployeeSession() {
        company = getSessionCompany();
    }

    public void printMenu() {
        System.out.println("Добро пожаловать, оператор\n");
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
            System.out.println("7. Добавить водителя/Сменить водителя для автомобиля");
            System.out.println("8. Добавить автомобиль/информацию по автомобилю");

            System.out.println("9. Закончить смену");

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
                    addDriver();
                    continue;
                }
                case 8: {
                    addCar();
                    continue;
                }
                case 9: {
                    closeSession();
                    break;
                }
                default: {
                    System.out.println("Некорректное значение, введите номер операции из списка выше");
                }
            }
        }
    }

    public static void printCategories() {
        System.out.println("Выберите номер атегории авто:");
        System.out.println("1. Легковое авто");
        System.out.println("2. Грузовое авто");
        System.out.println("3. Пассажирский транспорт");
        System.out.println("4. Краны");
    }

    protected CompanyCars getSessionCompany() {
        CompanyCars companyCars = new CompanyCars();
        //Берем информацию с последнего архива
        String fileName = getLastCreatedFileName();
        if(fileName == null) {
            setDefaultInfo(companyCars);
        }
        else {
            String fullPath = filePath + fileName;
            try (FileInputStream fileIn = new FileInputStream(fullPath)) {
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                companyCars = (CompanyCars) objectIn.readObject();
                objectIn.close();

            } catch (Exception ex) {
                //если с архивами проблемы, запишем стандартные значения
                setDefaultInfo(companyCars);
            }
        }
        return companyCars;
    }

    protected String getLastCreatedFileName(){
        File directory = new File(filePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = 0;
        File chosenFile = null;
        if (files != null)
        {
            for (File file : files)
            {
                if (file.lastModified() > lastModifiedTime)
                {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }
        if (chosenFile == null){
            return null;
        } else {
            return chosenFile.getName();
        }
    }

    protected void setDefaultInfo(CompanyCars company) {
        //В случае если нет файлов, записываю дефолтовые машины из урока
        String[] cars = {"C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20", "C100_2-50", "C200_2-40-1000", "C300_2-200-45", "C400_2-10-20",
                "C100_3-10", "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28", "C100_1-300", "C200_1-100-750", "C300_1-32-15"};
        for (String par : cars) {
            try {
                company.addCar(CarCreator.createCar(par));
            } catch (IllegalArgumentException e) {
                System.out.println("Автомобиль с параметрами " + par + " добавить не удалось.");
            }
        }
    }

    protected void printAllCost() {
        System.out.println("Общая стоимость расходов составляет " + company.getAllCost());
    }

    protected void printCategoriesCost() {
        printCategories();
        int cat = new Scanner(System.in).nextInt();
        cat = Integer.parseInt(cat + "00");
        System.out.println("Расход по выбранной категории составляет " + company.getCodeCost(cat));
    }

    protected void printMaxCostCategory() {
        company.printMaxCostCategory();
    }

    protected void printMinCostCategory() {
        company.printMinCostCategory();
    }

    protected void printCarsInfo() {
        printCategories();
        try {
            int cat = new Scanner(System.in).nextInt();
            cat = Integer.parseInt(cat + "00");
            company.printCategoryCars(cat);
        } catch (InputMismatchException e) {
            System.out.println("Некорректное значение, введите номер из списка выше");
        }

    }

    protected void printDriversInfo() {
        company.printDriversInfo();
    }

    protected void addDriver() {
        System.out.println("Введите ФИО водителя");
        String fio = new Scanner(System.in).nextLine();
        Driver driver = new Driver(fio);
        System.out.println("Выберите автомобиль:");
        ArrayList<Car> cars = company.getCars();
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(i + " - " + cars.get(i));
        }
        try {
            int carIndex = new Scanner(System.in).nextInt();
            if (carIndex < 0 || carIndex >= cars.size()) {
                throw new InputMismatchException();
            }
            company.setDriver(driver, cars.get(carIndex));
        } catch (InputMismatchException e) {
            System.out.println("Некорректное значение, введите номер из списка выше");
        }

    }

    protected void addCar() {
        System.out.println("Введите автомобиль в формате C(CODE_CAR)_гос номер-Пробег-(доп. параметр)");
        String params = new Scanner(System.in).nextLine();
        try {
            company.addCar(CarCreator.createCar(params));
        } catch (IllegalArgumentException e) {
            System.out.println("Автомобиль с параметрами " + params + " добавить не удалось.");
        }
    }

    protected void closeSession(){
        String fileName = filePath + format.format(new Date()) + ".txt";
        try(FileOutputStream fileOut = new FileOutputStream(fileName, false)) {
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(company);
            objectOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}