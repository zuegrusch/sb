package Company;

import Cars.Car;
import Driver.Driver;

import java.io.Serializable;
import java.util.*;

public class CompanyCars implements Serializable {
    //Расценки за топливо
    private Map<Integer, Double> fuelCost = new HashMap<>(){{
        put(100, 46.1);
        put(200, 48.9);
        put(300, 47.5);
        put(400, 48.9);
    }};

    private ArrayList<Car> cars = new ArrayList<>();

    public void addCar(Car car){
        boolean isUpdated = false;
        //проверяем, есть ли в компании такой автомобиль
        for(Car companyCar: cars){
            if(car.equals(companyCar)){
                companyCar.setMileage(companyCar.getMileage() + car.getMileage());
                companyCar.setParamVal(companyCar.getParamVal() + car.getParamVal());
                isUpdated = true;
                break;
            }
        }
        if(!isUpdated) {
            cars.add(car);
        }
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

    public void printCategoryCars(int code){
        //Фильтруем по коду
        ArrayList<Car> codeCars = new ArrayList<>();
        for (Car car: cars){
            if(car.getCode() == code){
                codeCars.add(car);
            }
        }
        //Сортируем по пробегу
        Collections.sort(codeCars, Comparator.comparingInt(Car::getMileage));
        for(Car car: codeCars){
            System.out.println(car);
        }
    }

    public void printDriversInfo(){
        for (Car car: cars){
            Driver driver = car.getDriver();
            if(driver != null){
                System.out.println(driver + ", закреплен за авто: " + car.getCodeName() +
                        ", госномер " + car.getGov_number());
            }
        }
    }

    public ArrayList<Driver> getDrivers(){
        ArrayList<Driver> drivers = new ArrayList<>();
        for (Car car: cars) {
            Driver driver = car.getDriver();
            if (driver != null) {
                drivers.add(driver);
            }
        }
        return drivers;
    }

    public ArrayList<Car> getCars(){
        return cars;
    }

    public void setDriver(Driver driver, Car car){
        for (Car companyCar: cars){
            if(companyCar.getDriver() != null && companyCar.getDriver().equals(driver) && !companyCar.equals(car)){
                companyCar.setDriver(null);
            }
            if(companyCar.equals(car)){
                companyCar.setDriver(driver);
            }
        }
    }

    public void removeDriver(Driver driver){
        for (Car companyCar: cars) {
            if (companyCar.getDriver() != null && companyCar.getDriver().equals(driver)) {
                companyCar.setDriver(null);
            }
        }
    }

    public void removeCar(Car car){
        cars.remove(car);
    }
}
