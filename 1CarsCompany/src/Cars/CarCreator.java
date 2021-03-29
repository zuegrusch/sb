package Cars;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CarCreator {

    private static void setParameters(String parameters, Car car) {
        Pattern pattern = Pattern.compile("C([0-9]+)_([0-9]+)-([0-9]+)(-[0-9]+)*");
        Matcher matcher = pattern.matcher(parameters);
        if (matcher.find()) {
            car.code = Integer.parseInt(matcher.group(1));
            car.gov_number = Integer.parseInt(matcher.group(2));
            car.mileage = Integer.parseInt(matcher.group(3));
            if (matcher.group(4) != null) {
                car.paramVal = Integer.parseInt(matcher.group(4).substring(1));
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static Car createCar(String params) throws IllegalArgumentException {
        if(!params.contains("_")) {
            throw new IllegalArgumentException();
        }
        Car car;
        int carCode = Integer.parseInt(params.substring(1, params.indexOf("_")));
        switch (carCode) {
            case 100:
                car = new Car();
                break;
            case 200:
                car = new CargoCar();
                break;
            case 300:
                car = new PassangerCar();
                break;
            case 400:
                car = new Crane();
                break;
            default:
                throw new IllegalArgumentException();
        }
        CarCreator.setParameters(params, car);
        return car;
    }

}
