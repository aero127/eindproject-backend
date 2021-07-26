package nl.mtbrental.eindproject.dto;

import nl.mtbrental.eindproject.model.Bike;

public class BikeDto {
    public Long id;
    public String bikeName;
    public int quantityTotal;
    public Long pricePerDay;

    public static BikeDto fromBike(Bike bike) {
        if (bike == null) return null;

        var dto = new BikeDto();
        dto.id = bike.getId();
        dto.bikeName = bike.getBikeName();
        dto.quantityTotal = bike.getQuantityTotal();
        dto.pricePerDay = bike.getPricePerDay();
        return dto;
    }
}
