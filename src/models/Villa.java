package models;

public class Villa extends Facility{
    private String roomStandard;
    private Double swimmingPoolAarea;
    private int numberOfFloors;

    public Villa(){
    }

    public Villa(String roomStandard, Double swimmingPoolAarea, int numberOfFloors) {
        this.roomStandard = roomStandard;
        this.swimmingPoolAarea = swimmingPoolAarea;
        this.numberOfFloors = numberOfFloors;
    }

    public Villa(String codeService, String nameService, Double useArea, Double rentalCost, int maxPerson, String typeOfRental, String roomStandard, Double swimmingPoolAarea, int numberOfFloors) {
        super(codeService, nameService, useArea, rentalCost, maxPerson, typeOfRental);
        this.roomStandard = roomStandard;
        this.swimmingPoolAarea = swimmingPoolAarea;
        this.numberOfFloors = numberOfFloors;
    }

    public String getRoomStandard() {
        return roomStandard;
    }

    public void setRoomStandard(String roomStandard) {
        this.roomStandard = roomStandard;
    }

    public Double getSwimmingPoolAarea() {
        return swimmingPoolAarea;
    }

    public void setSwimmingPoolAarea(Double swimmingPoolAarea) {
        this.swimmingPoolAarea = swimmingPoolAarea;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    @Override
    public String toString() {
        return "Villa{" +
                "roomStandard='" + roomStandard + '\'' +
                ", swimmingPoolAarea=" + swimmingPoolAarea +
                ", numberOfFloors=" + numberOfFloors +
                '}';
    }
    @Override
    public String getInfo() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s",super.getCodeService(),super.getNameService(),super.getUseArea(),super.getRentalCost(),super.getMaxPerson(),super.getTypeOfRental(),getRoomStandard(),getSwimmingPoolAarea(),getNumberOfFloors());

    }

}
