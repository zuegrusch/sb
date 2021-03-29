package Cars;

import Driver.Driver;

import java.io.Serializable;

public class Car implements Serializable {
    protected int code = 100;
    protected String codeName = "легковой автомобиль";
    protected int gov_number;
    protected int mileage;
    protected double fuelConsumption = 12.5;
    protected Driver driver;

    //доп параметр: значение, название, единица измерения
    protected int paramVal;
    protected String paramName;
    protected String paramUnit;

    //геттеры
    public int getCode() {
        return code;
    }

    public int getMileage() {
        return mileage;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public String getCodeName() {
        return codeName;
    }

    public int getGov_number() {
        return gov_number;
    }

    public int getParamVal() {
        return paramVal;
    }

    public Driver getDriver() {
        return driver;
    }

    //сеттеры
    public void setGov_number(int gov_number) {
        this.gov_number = gov_number;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setParamVal(int paramVal) {
        this.paramVal = paramVal;
    }

    @Override
    public String toString(){
        String result =  "Автомобиль из категории " + codeName + ". Госномер " + gov_number + ", пробег " + mileage;
        if (driver != null){
            result += ", Водитель: " + driver;
        }
        if(paramName != null){
            result += ", " + paramName + " - " + paramVal + " " + paramUnit;
        }
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return code == car.code &&
                gov_number == car.gov_number;
    }

}
