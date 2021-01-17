public class Crane extends Car{
    public Crane(String parameters){
        super(parameters);
        code = 400;
        codeName = "кран";
        paramName = "вес поднятых грузов";
        paramUnit = "тонн";
        fuelConsumption = 20.0;
    }
}
