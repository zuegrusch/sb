public class PassangerCar extends Car{
    public PassangerCar(String parameters){
        super(parameters);
        code = 300;
        codeName = "пассажирский транспорт";
        paramName = "число пассажиров";
        paramUnit = "";
        fuelConsumption = 11.5;
    }
}
