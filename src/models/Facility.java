package models;

public abstract class Facility {
    private String codeService;
    private String nameService;
    private Double useArea;
    private Double rentalCost;
    private int maxPerson;
    private String typeOfRental;
    public Facility(){
    }

    public Facility(String codeService, String nameService, Double useArea, Double rentalCost, int maxPerson, String typeOfRental) {
        this.codeService = codeService;
        this.nameService = nameService;
        this.useArea = useArea;
        this.rentalCost = rentalCost;
        this.maxPerson = maxPerson;
        this.typeOfRental = typeOfRental;
    }

    public String getCodeService() {
        return codeService;
    }

    public void setCodeService(String codeService) {
        this.codeService = codeService;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public Double getUseArea() {
        return useArea;
    }

    public void setUseArea(Double useArea) {
        this.useArea = useArea;
    }

    public Double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(Double rentalCost) {
        this.rentalCost = rentalCost;
    }

    public int getMaxPerson() {
        return maxPerson;
    }

    public void setMaxPerson(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    public String getTypeOfRental() {
        return typeOfRental;
    }

    public void setTypeOfRental(String typeOfRental) {
        this.typeOfRental = typeOfRental;
    }

    @Override
    public String toString() {
        return "Facility{" +
                "codeService='" + codeService + '\'' +
                ", nameService='" + nameService + '\'' +
                ", useArea=" + useArea +
                ", rentalCost=" + rentalCost +
                ", maxPerson=" + maxPerson +
                ", typeOfRental='" + typeOfRental + '\'' +
                '}';
    }

    public abstract String getInfo();
}
