import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CompanyCars {
    //Расценки за топливо
    private Map<Integer, Double> fuelCost = new HashMap<>(){{
        put(100, 46.1);
        put(200, 48.9);
        put(300, 47.5);
        put(400, 48.9);
    }};

    private ArrayList<Car> cars = new ArrayList<>();

    public void addCar(Car car){
        cars.add(car);
    }

    public double getCarCost(Car car){
        double carFuel = (car.getFuelConsumption() / 100) * car.getMileage();
        return carFuel * fuelCost.get(car.getCode());
    }

    public double getAllCost(){
        double cost = 0;
        for(Car car: cars){
            cost += getCarCost(car);
        }
        return cost;
    }

    public double getCodeCost(int code){
        double cost = 0;
        for (Car car: cars) {
            if(car.getCode() == code){
                cost += getCarCost(car);
            }
        }
        return cost;
    }

    public void printMaxCostCategory(){
        ArrayList<String> checkedCodes = new ArrayList<>();
        double maxCost = 0;
        String maxCostCategory="";
        for(Car car: cars){
            if(!checkedCodes.contains(car.getCodeName())){
                String codeName = car.getCodeName();
                checkedCodes.add(codeName);
                double cost = getCodeCost(car.getCode());
                if(cost > maxCost){
                    maxCost = cost;
                    maxCostCategory = codeName;
                }
            }
        }
        System.out.println("Самая наибольшая стоимость расходов у автомобилей из категории " + maxCostCategory);
    }

    public void printMinCostCategory(){
        ArrayList<String> checkedCodes = new ArrayList<>();
        double minCost = 0;
        String minCostCategory="";
        for(Car car: cars){
            if(!checkedCodes.contains(car.getCodeName())){
                String codeName = car.getCodeName();
                checkedCodes.add(codeName);
                double cost = getCodeCost(car.getCode());
                if(cost < minCost || minCost == 0){
                    minCost = cost;
                    minCostCategory = codeName;
                }
            }
        }
        System.out.println("Самая наименьшая стоимость расходов у автомобилей из категории " + minCostCategory);
    }

    //Припоминаю, что было задание выполнить эту часть без ArrayList
    public void printCategoryCars(int code){
        Car[] categoryCars = new Car[cars.size()];
        for(int i = 0,j = 0; i < cars.size(); i++){
            if(cars.get(i).getCode() == code){
                categoryCars[j] = cars.get(i);
                j++;
            }
        }
        categoryCars = sortCars(categoryCars);
        for(int i = 0; i < categoryCars.length && categoryCars[i] != null; i++){
            System.out.println(categoryCars[i]);
        }
    }

    public Car[] sortCars(Car[] cars){
        for(int i = 1; i < cars.length; i++){
            for(int j = i; j > 0 && cars[j] != null && cars[j-1] != null; j--){
                Car temp = cars[j];
                int miles = temp.getMileage();
                if(cars[j-1].getMileage() > miles){
                    cars[j] = cars[j-1];
                    cars[j-1] = temp;
                }
            }
        }
        return cars;
    }



}
