public class CargoCar extends Car {
    public CargoCar(String parameters){
        super(parameters);
        code = 200;
        codeName = "грузовой автомобиль";
        paramName = "объем груза";
        paramUnit = "см.куб.";
        fuelConsumption = 12.0;
    }

}
