import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Car {
    protected int code = 100;
    protected String codeName = "легковой автомобиль";
    protected int gov_number;
    protected int mileage;
    protected double fuelConsumption = 12.5;

    //доп параметр: значение, название, единица измерения
    protected int paramVal;
    protected String paramName;
    protected String paramUnit;

    public Car(String parameters){
        parseParameters(parameters);
    }

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

    private void parseParameters(String parameters){
        Pattern pattern = Pattern.compile("C([0-9]+)_([0-9]+)-([0-9]+)(-[0-9]+)*");
        Matcher matcher = pattern.matcher(parameters);
        if(matcher.find()){
            code = Integer.parseInt(matcher.group(1));
            gov_number = Integer.parseInt(matcher.group(2));
            mileage = Integer.parseInt(matcher.group(3));
            if(matcher.group(4) != null) {
                paramVal = Integer.parseInt(matcher.group(4).substring(1));
            }
        } else {
            System.out.println("Автомобиль с кодом "+ parameters + "создать не удалось.");
        }
    }

    @Override
    public String toString(){
        String result =  "Автомобиль из категории " + codeName + ". Госномер " + gov_number + ", пробег " + mileage;
        if(paramName != null){
            result += ", " + paramName + " - " + paramVal + " " + paramUnit;
        }
        return result;
    }

    //Есть ли взможность как-то более красиво создавать авто в зависимости от кода?
    public static Car createCar(String params){
        int carCode = Integer.parseInt(params.substring(1, params.indexOf("_")));
        switch (carCode){
            case 100: return new Car(params);
            case 200: return new CargoCar(params);
            case 300: return new PassangerCar(params);
            case 400: return new Crane(params);
        }
        return null;
    }
}
