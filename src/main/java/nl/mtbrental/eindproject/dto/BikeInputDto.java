package nl.mtbrental.eindproject.dto;

import nl.mtbrental.eindproject.model.Bike;

public class BikeInputDto {
    public String bikeName;
    public int quantityTotal;
    public int pricePerDay;

    public Bike toBike() {
        var bike = new Bike();
        bike.setBikeName(bikeName);
        bike.setQuantityTotal(quantityTotal);
        bike.setPricePerDay(pricePerDay);
        return bike;
    }
}
