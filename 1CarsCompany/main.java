import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String[] cars = {"C100_1-100", "C200_1-120-1200", "C300_1-120-30", "C400_1-80-20", "C100_2-50", "C200_2-40-1000", "C300_2-200-45", "C400_2-10-20",
                         "C100_3-10", "C200_3-170-1100", "C300_3-150-29", "C400_3-100-28", "C100_1-300", "C200_1-100-750", "C300_1-32-15"};
        CompanyCars company = new CompanyCars();
        for(String par : cars){
            company.addCar(Car.createCar(par));
        }

        while(true){
            System.out.println("Введите номер пункта из списка:");
            System.out.println("1. Общая стоимость расходов ГСМ");
            System.out.println("2. Расход по категории авто");
            System.out.println("3. Тип авто с наибольшей стоимостью расходов");
            System.out.println("4. Тип авто с наименьшей стоимостью расходов");
            System.out.println("5. Информация об авто");

            int command = new Scanner(System.in).nextInt();
            switch (command){
                case 1: {
                    System.out.println("Общая стоимость расходов составляет " + company.getAllCost());
                    continue;
                }
                case 2:{
                    printCategories();
                    int cat = new Scanner(System.in).nextInt();
                    cat = Integer.parseInt(cat + "00");
                    System.out.println("Расход по выбранной категории составляет " + company.getCodeCost(cat));
                    continue;
                }
                case 3:{
                    company.printMaxCostCategory();
                    continue;
                }
                case 4:{
                    company.printMinCostCategory();
                    continue;
                }
                case 5:{
                    printCategories();
                    int cat = new Scanner(System.in).nextInt();
                    cat = Integer.parseInt(cat + "00");
                    company.printCategoryCars(cat);
                    continue;
                }
            }
        }
    }

    public static void printCategories(){
        System.out.println("Выберите номер атегории авто:");
        System.out.println("1. Легковое авто");
        System.out.println("2. Грузовое авто");
        System.out.println("3. Пассажирский транспорт");
        System.out.println("4. Краны");
    }
}
