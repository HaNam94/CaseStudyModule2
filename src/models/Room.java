package models;

public class Room extends Facility{
    private String freeService;
    public Room(){
    }

    public Room(String freeService) {
        this.freeService = freeService;
    }

    public Room(String codeService, String nameService, Double useArea, Double rentalCost, int maxPerson, String typeOfRental, String freeService) {
        super(codeService, nameService, useArea, rentalCost, maxPerson, typeOfRental);
        this.freeService = freeService;
    }

    public String getFreeService() {
        return freeService;
    }

    public void setFreeService(String freeService) {
        this.freeService = freeService;
    }

    @Override
    public String toString() {
        return "Room{" +
                "freeService='" + freeService + '\'' +
                '}';
    }
    public String getInfo() {
        return String.format("%s,%s,%s,%s,%s,%s,%s",getCodeService(),getNameService(), getUseArea(),getRentalCost(),getMaxPerson(),getTypeOfRental(),getFreeService());
    }

}
